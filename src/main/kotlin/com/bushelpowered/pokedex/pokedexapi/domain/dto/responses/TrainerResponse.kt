package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

data class TrainerResponse(
        override val found: Boolean = true,
        val name: String,
        override val email: String
) : BaseTrainerResponse(
        found = found,
        email = email
)

abstract class BaseTrainerResponse(
        open val found: Boolean,
        open val email: String
)

data class TrainerErrorResponse(
        override val found: Boolean = true,
        val message: String,
        override val email: String
) : BaseTrainerResponse(
        found = found,
        email = email
)

data class TrainerLoginError(
        override val found: Boolean = true,
        val message: String,
        override val email: String,
        val password: String,
) : BaseTrainerResponse(
        found = found,
        email = email
)