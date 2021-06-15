package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity

data class Pokemon (
        val id: Int = 0,   // Int? allows id to be nullable, and '= null' sets the default value to null
        val name: String,
        val types: List<Type>,
        val height: Double,
        val weight: Double,
        val abilities: List<Ability>,
        val egg_groups: List<EggGroup>,
        val stats: Stats,
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
        hp = this.stats.hp,
        speed = this.stats.speed,
        attack = this.stats.attack,
        defense = this.stats.defense,
        special_attack = this.stats.special_attack,
        special_defense = this.stats.special_defense,
        genus = this.genus,
        description = this.description
)
