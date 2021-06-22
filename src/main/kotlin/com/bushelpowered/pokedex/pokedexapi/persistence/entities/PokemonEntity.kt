package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.*
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name = "pokemon")
data class PokemonEntity (
        @Id
        val id: Int? = null,
        val name: String,
        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
        @JoinTable(name = "trainer_pokemon",
                joinColumns = [(JoinColumn(name = "trainer_id", referencedColumnName = "id"))],
                inverseJoinColumns = [(JoinColumn(name = "poke_id", referencedColumnName = "id"))])
        val trainers: List<TrainerEntity>? = null,
        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(
                name = "pokemon_types",
                joinColumns = [JoinColumn(name = "poke_id")],
                inverseJoinColumns = [JoinColumn(name = "type_id")]
        )
        val types: List<TypeEntity>,
        val height: Double,
        val weight: Double,
        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(
                name = "pokemon_abilities",
                joinColumns = [JoinColumn(name = "poke_id")],
                inverseJoinColumns = [JoinColumn(name = "ability_id")]
        )
        val abilities: List<AbilityEntity>,
        @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.MERGE])
        @Fetch(value = FetchMode.SUBSELECT)
        @JoinTable(
                name = "pokemon_egg_groups",
                joinColumns = [JoinColumn(name = "poke_id")],
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