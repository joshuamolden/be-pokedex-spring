package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.NewTrainerRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.TrainerLoginRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.toDomain
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.*
import com.bushelpowered.pokedex.pokedexapi.service.TrainerService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/trainer")
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/register")
    fun registerNewTrainer(@RequestBody newTrainer: NewTrainerRequest): ResponseEntity<BaseTrainerResponse> {
        return when (trainerService.findTrainerByEmail(newTrainer.email)) {
            null -> ResponseEntity.ok(trainerService.addTrainer(newTrainer.toDomain()))
            else -> ResponseEntity.badRequest().body(TrainerErrorResponse(message = "Email already exists", email = newTrainer.email))
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody trainer: TrainerLoginRequest, response: HttpServletResponse): ResponseEntity<BaseTrainerResponse?> {
        val wrongInfoResponse = TrainerLoginError(false, "Invalid email or password", trainer.email, trainer.password)

        return when (val trainerLogin = trainerService.findTrainerByEmail(trainer.email)) {
            null -> ResponseEntity.badRequest().body(wrongInfoResponse)
            else -> if (trainerService.comparePassword(trainer)) {

                val issuer = trainer.email
                val jwt = Jwts.builder()
                        .setIssuer(issuer)
                        .setExpiration(Date(System.currentTimeMillis() + TEN_HOURS))
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact()

                val cookie = Cookie("jwt", jwt)
                cookie.isHttpOnly = true

                response.addCookie(cookie)

                ResponseEntity.ok(TrainerResponse(true, trainerLogin.name, trainerLogin.email))
            } else ResponseEntity.badRequest().body(wrongInfoResponse)

        }
    }

    @GetMapping("/authenticate")
    fun getAuthenticatedTrainer(@CookieValue("jwt") jwt: String?): ResponseEntity<BaseTrainerResponse> {
        val unauthenticated = TrainerAuthError(message = "unauthenticated trainer")
        try {
            if (jwt == null) return ResponseEntity(unauthenticated, HttpStatus.UNAUTHORIZED)
            val jwtClaim = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwt).body
            return ResponseEntity.ok(trainerService.findTrainerByEmail(jwtClaim.issuer))
        } catch (e: Exception) {
            return ResponseEntity(unauthenticated, HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("/logout")
    fun logout(response: HttpServletResponse): ResponseEntity<TrainerLogout> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0
        response.addCookie(cookie)
        return ResponseEntity(TrainerLogout(message = "Successfully logged out"), HttpStatus.OK)
    }

//    @PostMapping("capture")
//    fun capturePokemon(@RequestBody pokemonId: CapturePokemonRequest) : ResponseEntity<CapturePokemonResponse> {
//        val pokemon =
//    }

    private final val TEN_HOURS = 60 * 10 * 1000
    private final val SECRET_KEY = "secret"
}

/*
{
   "name": "test",
   "email": "test@gmail.com",
   "password": "testPassword"
}
for testing  */