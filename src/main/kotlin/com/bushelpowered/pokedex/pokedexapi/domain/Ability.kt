package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.AbilityResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity

data class Ability (
        val id: Int? = null,
        val name: String
        )

fun Ability.toEntity(): AbilityEntity = AbilityEntity (
        id = this.id,
        name = this.name
        )

fun Ability.toResponse(): AbilityResponse = AbilityResponse (
        id = this.id,
        name = this.name
        )
