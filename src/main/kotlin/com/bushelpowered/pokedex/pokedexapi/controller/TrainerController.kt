package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.NewTrainerRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.toDomain
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.LoginResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.RegisterTrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TrainerEntity
import com.bushelpowered.pokedex.pokedexapi.service.TrainerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/trainer")
class TrainerController(private val trainerService: TrainerService) {

    @PostMapping("/register")
    fun registerNewTrainer(@RequestBody newTrainer: NewTrainerRequest): ResponseEntity<TrainerResponse> {
        return ResponseEntity.ok(trainerService.addTrainer(newTrainer.toDomain()))
    }

//    @PostMapping("/login")
//    fun login(@RequestBody trainer: TrainerEntity): ResponseEntity<TrainerEntity?> {
//        val response = trainerService.findByEmail(trainer.email) ?:
//        return ResponseEntity.badRequest().body("User not found")
//    }
}