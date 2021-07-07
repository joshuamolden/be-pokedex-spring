package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.NewTrainerRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.TrainerLoginRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.CapturePokemonResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.PokemonListResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.models.*
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.TrainerDao
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.CapturedPokemonRepository
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.PokemonRepository
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.TrainerRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.servlet.http.Cookie

@Service
class TrainerService {

    @Autowired
    lateinit var trainerRepository: TrainerRepository

    @Autowired
    lateinit var pokemonRepository: PokemonRepository

    @Autowired
    lateinit var capturedPokemonRepository: CapturedPokemonRepository

    private val passwordEncoder = BCryptPasswordEncoder()

    fun addTrainer(newTrainer: NewTrainerRequest): TrainerResponse {
        return trainerRepository.findByEmail(newTrainer.email)?.toResponse() ?: trainerRepository.save(
                Trainer(
                        name = newTrainer.name,
                        email = newTrainer.email,
                        password = passwordEncoder.encode(newTrainer.password))).toResponse()
    }

    fun findTrainerByEmail(email: String): TrainerResponse? {
        return trainerRepository.findByEmail(email)?.toResponse()
    }

    fun comparePassword(trainer: TrainerLoginRequest): Boolean {
        return BCryptPasswordEncoder().matches(trainer.password, trainerRepository.findByEmail(trainer.email)!!.password)   // wont be null if comparing passwords
    }

    fun trainerCapturesPokemon(pokemonId: Int, trainerEmail: String): CapturePokemonResponse? {
        val pokemon = pokemonRepository.findById(pokemonId) ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Pokemon doesn't exist")
        val trainer = trainerRepository.findByEmail(trainerEmail)
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Trainer not found")
        return when (capturedPokemonRepository.checkIfCaptured(trainer.id!!, pokemonId)?.toResponse()) {
            null -> capturedPokemonRepository.save(CapturedPokemon(trainer = trainer, pokemon = pokemon)).toResponse()
            else -> CapturePokemonResponse("Pokemon already caught", pokemon)
        }
    }

    fun getAllCapturedPokemon(pageable: Pageable, trainerEmail: String): Page<PokemonListResponse?> {
        val trainerId: Int = trainerRepository.findByEmail(trainerEmail)?.id!!
        return capturedPokemonRepository.findById(pageable, trainerId).map { it?.pokemon?.toListResponse() }
    }

    fun jwtParser(jwt: String): String {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).body.issuer
    }

    fun createJwt(email: String): Cookie {
        val jwt = Jwts.builder()
                .setIssuer(email)
                .setExpiration(Date(System.currentTimeMillis() + TEN_HOURS))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact()

        return Cookie("jwt", jwt)
    }

    private final val TEN_HOURS = 60 * 60 * 10 * 1000 // 1000 miliseconds = 1 second
    private final val SECRET_KEY = "secret"
}