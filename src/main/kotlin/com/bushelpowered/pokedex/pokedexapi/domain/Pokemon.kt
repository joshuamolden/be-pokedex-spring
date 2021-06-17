package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.StatsEntity

data class Pokemon (
        val id: Int? = null,   // Int? allows id to be nullable, and '= null' sets the default value to null
        val name: String,
        val types: List<Type>,
        val height: Double,
        val weight: Double,
        val abilities: List<Ability>,
        val egg_groups: List<EggGroup>,
        val genus: String,
        val description: String
        )

fun Pokemon.toEntity(): PokemonEntity = PokemonEntity (
        id = this.id,
        name = this.name,
        types = this.types.map { type -> type.toEntity() },
        height = this.height,
        weight = this.weight,
        abilities = this.abilities.map { ability -> ability.toEntity() },
        egg_groups = this.egg_groups.map { egg_group -> egg_group.toEntity() },
        genus = this.genus,
        description = this.description
)

fun Pokemon.toResponse(): PokemonResponse = PokemonResponse(
        id = id!!,
        name = name,
        types = types.map { type -> type.name },
        height = height,
        weight = weight,
        abilities = abilities.map { ability -> ability.name },
        egg_groups = egg_groups.map { eggGroup -> eggGroup.name },
        genus = genus,
        description = description,
)
