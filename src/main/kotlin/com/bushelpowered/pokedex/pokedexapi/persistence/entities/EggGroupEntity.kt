package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.EggGroup
import javax.persistence.*

@Table(name = "Egg_Group")
@Entity
data class EggGroupEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val name: String
        )

fun EggGroupEntity.toDomain(): EggGroup = EggGroup (
        name = this.name
        )
