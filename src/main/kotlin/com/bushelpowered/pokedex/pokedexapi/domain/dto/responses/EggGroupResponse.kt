package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

import com.bushelpowered.pokedex.pokedexapi.domain.models.EggGroup

data class EggGroupResponse(
        val id: Int? = null,
        val name: String
)

fun EggGroupResponse.toDomain(): EggGroup = EggGroup(
        id = this.id,
        name = this.name
)