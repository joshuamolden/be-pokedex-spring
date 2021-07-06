package com.bushelpowered.pokedex.pokedexapi.domain.models

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TrainerEntity

data class Trainer(
        val id: Int? = null,
        val name: String = "",
        val email: String,
        val password: String
)

fun Trainer.toEntity(): TrainerEntity = TrainerEntity(
        id = this.id,
        name = this.name,
        email = this.email,
        password = this.password
)

fun Trainer.toResponse(): TrainerResponse = TrainerResponse(
        name = this.name,
        email = this.email
)