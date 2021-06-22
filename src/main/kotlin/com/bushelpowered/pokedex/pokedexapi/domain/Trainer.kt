package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TrainerEntity
data class Trainer (
        val user_name: String,
        val email: String,
        val password: String,
        val pokemon_list: List<Pokemon>
        )

fun Trainer.ToEntity(): TrainerEntity = TrainerEntity (
        user_name = this.user_name,
        email = this.email,
        password = this.password,
        pokemon_list = this.pokemon_list.map { pokemon -> pokemon.toEntity() }
)