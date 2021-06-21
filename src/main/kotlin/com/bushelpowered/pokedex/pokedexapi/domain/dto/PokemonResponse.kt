package com.bushelpowered.pokedex.pokedexapi.domain.dto

data class PokemonDetailsResponse (
        val id: Int,
        val name: String,
        val types: List<String>,
        val height: Double,
        val weight: Double,
        val abilities: List<String>,
        val egg_groups: List<String>,
        val stats: Map<String, Int>,
        val genus: String,
        val description: String,
)