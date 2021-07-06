package com.bushelpowered.pokedex.pokedexapi.domain.dto.requests

import com.bushelpowered.pokedex.pokedexapi.domain.models.Trainer

data class NewTrainerRequest(
        val name: String,
        val email: String,
        val password: String
)

fun NewTrainerRequest.toDomain(): Trainer = Trainer(
        name = this.name,
        email = this.email,
        password = this.password
)