package com.bushelpowered.pokedex.pokedexapi.domain

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.StatsEntity

data class Stats (
        val hp: Int,
        val speed: Int,
        val attack: Int,
        val defense: Int,
        val special_attack: Int,
        val special_defense: Int
        )

fun Stats.toEntity(): StatsEntity = StatsEntity (
        hp = this.hp,
        speed = this.speed,
        attack = this.attack,
        defense = this.defense,
        special_attack = this.special_attack,
        special_defense = this.special_defense
        )