package com.bushelpowered.pokedex.pokedexapi.domain.dto.requests

import com.bushelpowered.pokedex.pokedexapi.domain.Trainer

class TrainerLoginRequest(
        val email: String,
        val password: String
)

fun TrainerLoginRequest.toDomain(): Trainer = Trainer(
        email = this.email,
        password = this.password
)