package com.bushelpowered.pokedex.pokedexapi.Domain

import javax.persistence.*

data class Pokemon (
        val id: Int? = null,   // Int? allows poke_id to be nullable, and = null sets the default value to null
        val name: String,
        val types: List<Type>,
        val height: Double,
        val weight: Double,
        val abilities: List<Ability>,
        val egg_groups: List<EggGroup>,
        val genus: String,
        val description: String
        )

@Entity
data class PokemonEntity (
        @Id
        val id: Int? = null,   // Int? allows poke_id to be nullable, and = null sets the default value to null
        val name: String,
        @ManyToMany
        @JoinTable(
                name = "pokemon_type",
                joinColumns = @JoinColumn(name = )
        )
        val types: List<Type>,
        val height: Double,
        val weight: Double,
        val abilities: List<Ability>,
        val egg_groups: List<EggGroup>,
        val genus: String,
        val description: String
)

fun Pokemon.toEntity(): PokemonEntity {
        return PokemonEntity(
                id = this.id,

        )
}