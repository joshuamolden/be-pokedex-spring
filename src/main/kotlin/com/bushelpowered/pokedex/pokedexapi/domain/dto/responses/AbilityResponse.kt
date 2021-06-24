package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

import com.bushelpowered.pokedex.pokedexapi.domain.Ability

data class AbilityResponse (
        val id: Int? = null,
        val name: String
        )

fun AbilityResponse.toDomain(): Ability = Ability (
        id = this.id,
        name = this.name
        )