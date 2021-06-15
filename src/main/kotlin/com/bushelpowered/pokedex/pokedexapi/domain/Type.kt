package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity

data class Type (
        val name: String
        )

fun Type.toEntity(): TypeEntity = TypeEntity (
        name = this.name
        )