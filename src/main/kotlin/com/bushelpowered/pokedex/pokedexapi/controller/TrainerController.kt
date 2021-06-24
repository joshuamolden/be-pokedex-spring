package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.NewTrainerRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.TrainerLoginRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.toDomain
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.BaseTrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerErrorResponse
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
            else -> ResponseEntity.badRequest().body(TrainerErrorResponse(false, "Email already exists", newTrainer.email))
        }
    }

    @PostMapping("/login")
    @Transactional
    fun login(@RequestBody trainer: TrainerLoginRequest): ResponseEntity<TrainerResponse?> {
        val result = trainerService.findTrainerByEmail(trainer.email)
                ?: return ResponseEntity.badRequest().body(TrainerResponse(false, "User not found", trainer.email))
        return ResponseEntity.ok(result)
    }
}