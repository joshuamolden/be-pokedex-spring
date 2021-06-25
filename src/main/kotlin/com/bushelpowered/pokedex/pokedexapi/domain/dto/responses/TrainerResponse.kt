package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

abstract class BaseTrainerResponse(
        open val found: Boolean
)

data class TrainerResponse(
        override val found: Boolean = true,
        val name: String,
        val email: String
) : BaseTrainerResponse(
        found = found
)

data class TrainerErrorResponse(
        override val found: Boolean = true,
        val message: String,
        val email: String
) : BaseTrainerResponse(
        found = found
)

data class TrainerLoginError(
        override val found: Boolean = true,
        val message: String,
        val email: String,
        val password: String,
) : BaseTrainerResponse(
        found = found
)

data class TrainerAuthError(
        override val found: Boolean = false,
        val message: String
) : BaseTrainerResponse(
        found = found
)

data class TrainerLogout(
        override val found: Boolean = true,
        val message: String
) : BaseTrainerResponse(
        found = found
)