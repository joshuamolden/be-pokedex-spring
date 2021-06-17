package com.bushelpowered.pokedex.pokedexapi.persistence.entities

data class PokemonResponse (
        val id: Int,
        val name: String,
        val types: List<String>,
        val height: Double,
        val weight: Double,
        val abilities: List<String>,
        val egg_groups: List<String>,
        val genus: String,
        val description: String,
)