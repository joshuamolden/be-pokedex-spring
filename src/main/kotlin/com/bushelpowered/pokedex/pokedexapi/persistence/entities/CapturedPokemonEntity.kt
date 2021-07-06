package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.CapturedPokemon
import javax.persistence.*

@Entity
@Table(name = "captured_pokemon")
data class CapturedPokemonEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @ManyToOne
        @JoinColumn(name = "trainer_id")
        val trainer: TrainerEntity,
        @ManyToOne
        @JoinColumn(name = "poke_id")
        val pokemon: PokemonEntity,
)

fun CapturedPokemonEntity.toDomain(): CapturedPokemon = CapturedPokemon(
        id = this.id,
        trainer = this.trainer.toDomain(),
        pokemon = this.pokemon.toDomain()
)