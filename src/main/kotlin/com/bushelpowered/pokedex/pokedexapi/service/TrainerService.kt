package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.Trainer
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.TrainerLoginRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.CapturePokemonResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.PokemonListResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.toEntity
import com.bushelpowered.pokedex.pokedexapi.domain.toListResponse
import com.bushelpowered.pokedex.pokedexapi.domain.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.CapturedPokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.CapturedPokemonRepository
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.PokemonRepository
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.TrainerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TrainerService {

    @Autowired
    lateinit var trainerRepository: TrainerRepository

    @Autowired
    lateinit var pokemonRepository: PokemonRepository

    @Autowired
    lateinit var capturedPokemonRepository: CapturedPokemonRepository

    private val passwordEncoder = BCryptPasswordEncoder()

    fun addTrainer(newTrainer: Trainer): TrainerResponse {
        return trainerRepository.findByEmail(newTrainer.email)?.toDomain()?.toResponse() ?: trainerRepository.save(
                Trainer(
                        name = newTrainer.name,
                        email = newTrainer.email,
                        password = passwordEncoder.encode(newTrainer.password)).toEntity()).toDomain().toResponse()
    }

    fun findTrainerByEmail(email: String): TrainerResponse? {
        return trainerRepository.findByEmail(email)?.toDomain()?.toResponse()
    }

    fun comparePassword(trainer: TrainerLoginRequest): Boolean {
        return BCryptPasswordEncoder().matches(trainer.password, trainerRepository.findByEmail(trainer.email)!!.password)   // wont be null if in this block
    }

    fun trainerCapturesPokemon(pokemonId: Int, trainerEmail: String): CapturePokemonResponse? {
        val pokemon = pokemonRepository.findById(pokemonId).get()
        val trainer = trainerRepository.findByEmail(trainerEmail)
                ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Trainer not found")
        return when (capturedPokemonRepository.checkIfCaptured(trainer.id!!, pokemonId)?.toDomain()?.toResponse()) {
            null -> capturedPokemonRepository.save(CapturedPokemonEntity(trainer = trainer, pokemon = pokemon)).toDomain().toResponse()
            else -> CapturePokemonResponse("Pokemon already caught", pokemon.toDomain())
        }
    }

    fun getAllCapturedPokemon(pageable: Pageable, trainerEmail: String): Page<PokemonListResponse?> {
        val trainerId: Int = trainerRepository.findByEmail(trainerEmail)?.id!!
        return capturedPokemonRepository.findByTrainerId(pageable, trainerId).map { it?.toDomain()?.pokemon?.toListResponse() }
    }
}