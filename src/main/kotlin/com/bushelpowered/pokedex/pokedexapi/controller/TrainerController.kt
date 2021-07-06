package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.NewTrainerRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.TrainerLoginRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.*
import com.bushelpowered.pokedex.pokedexapi.service.TrainerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api/v1/trainer")
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/register")
    fun registerNewTrainer(@RequestBody newTrainer: NewTrainerRequest): ResponseEntity<BaseTrainerResponse> {
        return when (trainerService.findTrainerByEmail(newTrainer.email)) {
            null -> ResponseEntity.ok(trainerService.addTrainer(newTrainer))
            else -> ResponseEntity.badRequest().body(TrainerErrorResponse(message = "Email already exists", email = newTrainer.email))
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody trainer: TrainerLoginRequest, response: HttpServletResponse): ResponseEntity<BaseTrainerResponse?> {
        val wrongInfoResponse = TrainerLoginError(false, "Invalid email or password", trainer.email, trainer.password)
        return when (val trainerLogin = trainerService.findTrainerByEmail(trainer.email)) {
            null -> ResponseEntity.badRequest().body(wrongInfoResponse)
            else -> if (trainerService.comparePassword(trainer)) {
                val cookie = trainerService.createJwt(trainer.email)
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
            return ResponseEntity.ok(trainerService.findTrainerByEmail(trainerService.jwtParser(jwt)))
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

    @PostMapping("/capture/{pokemon_id}")
    fun capturePokemon(
            @PathVariable pokemon_id: Int,
            @CookieValue("jwt") jwt: String?
    ): ResponseEntity<CapturePokemonResponse> {
        return when (jwt) {
            null -> ResponseEntity.badRequest().body(CapturePokemonResponse("You must be signed in to capture pokemon", null))
            else -> if (pokemon_id > 553) ResponseEntity.badRequest().body(CapturePokemonResponse("Pokemon doesn't exist", null))
            else ResponseEntity.ok(trainerService.trainerCapturesPokemon(pokemon_id, trainerService.jwtParser(jwt)))
        }
    }

    @GetMapping("/captured")
    fun capturedPokemon(
            @PageableDefault(sort = ["id"], value = 15) pageable: Pageable,
            @CookieValue("jwt") jwt: String?
    ): ResponseEntity<Page<PokemonListResponse?>> {
        return if (jwt.isNullOrBlank()) ResponseEntity.badRequest().body(Page.empty())
        else {
            ResponseEntity.ok(trainerService.getAllCapturedPokemon(pageable, trainerService.jwtParser(jwt)))
        }
    }
}