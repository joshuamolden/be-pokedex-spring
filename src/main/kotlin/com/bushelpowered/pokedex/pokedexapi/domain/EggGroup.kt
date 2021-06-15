package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity

data class EggGroup (
        val name: String
        )

fun EggGroup.toEntity(): EggGroupEntity = EggGroupEntity(
        name = this.name
)