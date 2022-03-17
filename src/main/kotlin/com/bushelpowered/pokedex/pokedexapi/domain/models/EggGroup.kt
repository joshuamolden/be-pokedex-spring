package com.bushelpowered.pokedex.pokedexapi.domain.models

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.EggGroupResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity

data class EggGroup(
        val id: Int? = null,
        val name: String
)

fun EggGroup.toEntity(): EggGroupEntity = EggGroupEntity(
        id = this.id,
        name = this.name
)

fun EggGroup.toResponse(): EggGroupResponse = EggGroupResponse(
        id = this.id,
        name = this.name
)