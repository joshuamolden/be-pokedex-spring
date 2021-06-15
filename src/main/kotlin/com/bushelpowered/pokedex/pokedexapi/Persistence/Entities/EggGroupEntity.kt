package com.bushelpowered.pokedex.pokedexapi.Persistence.Entities

import com.bushelpowered.pokedex.pokedexapi.Domain.EggGroup
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
