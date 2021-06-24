package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.Trainer
import com.bushelpowered.pokedex.pokedexapi.domain.dto.requests.TrainerLoginRequest
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.BaseTrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerErrorResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.toEntity
import com.bushelpowered.pokedex.pokedexapi.domain.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.TrainerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class TrainerService(private val trainerRepository: TrainerRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun addTrainer(newTrainer: Trainer): TrainerResponse {
        return trainerRepository.findByEmail(newTrainer.email)?.toDomain()?.toResponse() ?: trainerRepository.save(
                Trainer(
                        name = newTrainer.name,
                        email = newTrainer.email,
                        password = passwordEncoder.encode(newTrainer.password)).toEntity()).toDomain().toResponse()
    }

    fun findTrainerByEmail(email: String) : TrainerResponse? {
        return trainerRepository.findByEmail(email)?.toDomain()?.toResponse()
    }

    fun comparePassword(trainer: TrainerLoginRequest): Boolean {
        return BCryptPasswordEncoder().matches(trainer.password, trainerRepository.findByEmail(trainer.email)!!.password)   // wont be null if passowrd is checked
    }
}