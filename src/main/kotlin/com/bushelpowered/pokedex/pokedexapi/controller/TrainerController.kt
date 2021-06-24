package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.NewTrainerRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.TrainerLoginRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.toDomain
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.BaseTrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerErrorResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerLoginError
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.service.TrainerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.transaction.Transactional

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
    fun login(@RequestBody trainer: TrainerLoginRequest): ResponseEntity<BaseTrainerResponse?> {
        val wrongInfoResponse = TrainerLoginError(false, "Invalid email or password", trainer.email, trainer.password)
        val trainerLogin = trainerService.findTrainerByEmail(trainer.email)
        return when(trainerLogin) {
            null -> ResponseEntity.badRequest().body(wrongInfoResponse)
            else -> if (trainerService.comparePassword(trainer))
                        ResponseEntity.ok(TrainerResponse(true, trainerLogin.name, trainerLogin.email))
                    else ResponseEntity.badRequest().body(wrongInfoResponse)
        }
    }
}