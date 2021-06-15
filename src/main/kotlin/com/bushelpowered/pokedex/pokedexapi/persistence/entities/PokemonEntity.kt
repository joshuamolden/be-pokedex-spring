package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.*
import javax.persistence.*

@Table(name = "Pokemon")
@Entity
data class PokemonEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int = 0,
        val name: String,
        @ManyToMany
        @JoinTable(
                name = "pokemon_types",
                joinColumns = [JoinColumn(name = "pokemon_id")],
                inverseJoinColumns = [JoinColumn(name = "type_id")]
        )
        val types: List<TypeEntity>,
        val height: Double,
        val weight: Double,
        @ManyToMany
        @JoinTable(
                name = "pokemon_abilities",
                joinColumns = [JoinColumn(name = "pokemon_id")],
                inverseJoinColumns = [JoinColumn(name = "ability_id")]
        )
        val abilities: List<AbilityEntity>,
        @ManyToMany
        @JoinTable(
                name = "pokemon_egg_groups",
                joinColumns = [JoinColumn(name = "pokemon_id")],
                inverseJoinColumns = [JoinColumn(name = "egg_group_id")]
        )
        val egg_groups: List<EggGroupEntity>,
        val hp: Int,
        val speed: Int,
        val attack: Int,
        val defense: Int,
        val special_attack: Int,
        val special_defense: Int,
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
        stats = Stats (
                hp = this.hp,
                speed = this.speed,
                attack = this.attack,
                defense = this.defense,
                special_attack = this.special_attack,
                special_defense = this.special_defense
                ),
        genus = this.genus,
        description = this.description
)