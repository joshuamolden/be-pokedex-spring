package com.bushelpowered.pokedex.pokedexapi.Domain

import com.bushelpowered.pokedex.pokedexapi.Persistence.Entities.AbilityEntity
import javax.persistence.*

data class Ability (
        val name: String
        )

fun Ability.toEntity(): AbilityEntity = AbilityEntity (
        name = this.name
        )
