package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

import com.bushelpowered.pokedex.pokedexapi.domain.models.Type

data class TypeResponse(
        val id: Int? = null,
        val name: String
)

fun TypeResponse.toDomain(): Type = Type(
        id = this.id,
        name = this.name
)