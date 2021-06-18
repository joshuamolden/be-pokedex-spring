package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.Stats
import javax.persistence.*

@Entity
@Table(name = "stats")
data class StatEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        val name: String,
        val value: Int
)
//
//fun StatEntity.toDomain(): Stats = Stats (
//        name = this.name,
//        value = this.value
//)