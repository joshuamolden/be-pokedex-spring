package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.CapturedPokemon
import javax.persistence.*

@Entity
@Table(name = "captured_pokemon")
data class CapturedPokemonEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int,
        val trainer_id: Int,
        val poke_id: Int,
        )

fun CapturedPokemonEntity.toDomain(): CapturedPokemon = CapturedPokemon(
        id = this.id,
        trainer_id = this.trainer_id,
        poke_id = this.poke_id
)