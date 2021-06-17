package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.Stats
import javax.persistence.*

@Entity
@Table(name = "stats")
data class StatsEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = 2,
        val hp: Int,
        val speed: Int,
        val attack: Int,
        val defense: Int,
        val special_attack: Int,
        val special_defense: Int
)

fun StatsEntity.toDomain(): Stats = Stats (
        hp = this.hp,
        speed = this.speed,
        attack = this.attack,
        defense = this.defense,
        special_attack = this.special_attack,
        special_defense = this.special_defense
)