package com.bushelpowered.pokedex.pokedexapi.Domain

import com.bushelpowered.pokedex.pokedexapi.Persistence.Entities.TypeEntity
import javax.persistence.Entity

data class Type (
        val name: String
        )

fun Type.toEntity(): TypeEntity = TypeEntity (
        name = this.name
        )