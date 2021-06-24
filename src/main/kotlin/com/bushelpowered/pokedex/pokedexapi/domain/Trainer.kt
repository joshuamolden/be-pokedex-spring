package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TrainerResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TrainerEntity
data class Trainer (
        val name: String,
        val email: String,
        val password: String,
        val pokemon_list: List<Pokemon>? = null
        )

fun Trainer.toEntity(): TrainerEntity = TrainerEntity (
        name = this.name,
        email = this.email,
        password = this.password,
        pokemon_list = this.pokemon_list?.map { pokemon -> pokemon.toEntity() }
)
fun Trainer.toResponse(): TrainerResponse = TrainerResponse (
        name = this.name,
        email = this.name
        )