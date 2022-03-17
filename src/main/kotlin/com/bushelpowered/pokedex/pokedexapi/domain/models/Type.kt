package com.bushelpowered.pokedex.pokedexapi.domain.models

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TypeResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity

data class Type(
        val id: Int? = null,
        val name: String
)

fun Type.toEntity(): TypeEntity = TypeEntity(
        id = this.id,
        name = this.name
)

fun Type.toResponse(): TypeResponse = TypeResponse(
        id = this.id,
        name = this.name
)