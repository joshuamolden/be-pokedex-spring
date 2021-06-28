package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.CapturePokemonResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.CapturedPokemonEntity

data class CapturedPokemon (
        val id: Int? = null,
        val trainer: Trainer,
        val pokemon: Pokemon
        )

fun CapturedPokemon.toEntity(): CapturedPokemonEntity = CapturedPokemonEntity(
        id = this.id,
        trainer = this.trainer.toEntity(),
        pokemon = this.pokemon.toEntity()
)

fun CapturedPokemon.toResponse(): CapturePokemonResponse = CapturePokemonResponse(
        message = "Captured Pokemon",
        pokemon = this.pokemon
)