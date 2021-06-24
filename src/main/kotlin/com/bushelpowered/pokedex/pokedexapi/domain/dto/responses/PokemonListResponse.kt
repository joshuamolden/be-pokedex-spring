package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

import com.bushelpowered.pokedex.pokedexapi.domain.Pokemon

class PokemonListResponse (
        val id: Int,
        val name: String,
        val types: List<String>
)