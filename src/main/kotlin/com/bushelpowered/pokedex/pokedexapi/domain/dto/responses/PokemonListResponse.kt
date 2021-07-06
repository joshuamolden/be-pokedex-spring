package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

class PokemonListResponse(
        val id: Int,
        val name: String,
        val image: String?,
        val types: List<String>
)