package com.bushelpowered.pokedex.pokedexapi.Domain

import com.bushelpowered.pokedex.pokedexapi.Persistence.Entities.EggGroupEntity

data class EggGroup (
        val name: String
        )

fun EggGroup.toEntity(): EggGroupEntity = EggGroupEntity(
        name = this.name
)