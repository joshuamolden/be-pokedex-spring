package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.*
import com.bushelpowered.pokedex.pokedexapi.domain.Stats
import javax.persistence.*

@Entity
@Table(name = "pokemon")
data class PokemonEntity (
        @Id
        val id: Int? = null,
        val name: String,
        @ManyToMany
        @JoinTable(
                name = "pokemon_types",
                joinColumns = [JoinColumn(name = "poke_id")],
                inverseJoinColumns = [JoinColumn(name = "type_id")]
        )
        val types: List<TypeEntity>,
        val height: Double,
        val weight: Double,
        @ManyToMany
        @JoinTable(
                name = "pokemon_abilities",
                joinColumns = [JoinColumn(name = "poke_id")],
                inverseJoinColumns = [JoinColumn(name = "ability_id")]
        )
        val abilities: List<AbilityEntity>,
        @ManyToMany
        @JoinTable(
                name = "pokemon_egg_groups",
                joinColumns = [JoinColumn(name = "poke_id")],
                inverseJoinColumns = [JoinColumn(name = "egg_group_id")]
        )
        val egg_groups: List<EggGroupEntity>,
        val genus: String,
        val description: String
)

fun PokemonEntity.toDomain(): Pokemon = Pokemon (
        id = this.id,
        name = this.name,
        types = this.types.map { typeEntity -> typeEntity.toDomain() },
        height = this.height,
        weight = this.weight,
        abilities = this.abilities.map { abilityEntity -> abilityEntity.toDomain() },
        egg_groups = this.egg_groups.map { egg_groupEntity -> egg_groupEntity.toDomain() },
        genus = this.genus,
        description = this.description
)