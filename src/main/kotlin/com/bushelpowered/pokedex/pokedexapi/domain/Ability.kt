package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity

data class Ability (
        val name: String
        )

fun Ability.toEntity(): AbilityEntity = AbilityEntity (
        name = this.name
        )
