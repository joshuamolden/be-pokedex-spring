package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.CapturedPokemonEntity

data class CapturedPokemon (
        val id: Int,
        val trainer_id: Int,
        val poke_id: Int
        )

fun CapturedPokemon.toEntity(): CapturedPokemonEntity = CapturedPokemonEntity(
        id = this.id,
        trainer_id = this.trainer_id,
        poke_id = this.poke_id
)