package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.Trainer
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.domain.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TrainerEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.TrainerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class TrainerService(private val trainerRepository: TrainerRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun addTrainer(newTrainer: Trainer): TrainerResponse {
        return trainerRepository.findByEmail(newTrainer.email)?.toDomain()?.toResponse() ?:
        trainerRepository.save(
                TrainerEntity(
                        name = newTrainer.name,
                        email = newTrainer.email,
                        password = passwordEncoder.encode(newTrainer.password))).toDomain().toResponse()
        }

    fun findByEmail(email: String): TrainerResponse? {
        return trainerRepository.findByEmail(email)?.toDomain()?.toResponse()
    }

    private fun comparePassword(password: String, trainerPassword: String): Boolean {
        return BCryptPasswordEncoder().matches(password, trainerPassword)
    }
}