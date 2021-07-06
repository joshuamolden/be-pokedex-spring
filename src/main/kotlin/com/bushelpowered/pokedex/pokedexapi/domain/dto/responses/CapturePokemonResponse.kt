package com.bushelpowered.pokedex.pokedexapi.domain.dto.responses

import com.bushelpowered.pokedex.pokedexapi.domain.models.Pokemon

data class CapturePokemonResponse(
        val message: String,
        val pokemon: Pokemon
)