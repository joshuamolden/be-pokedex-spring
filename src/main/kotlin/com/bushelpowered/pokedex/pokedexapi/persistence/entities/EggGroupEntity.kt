package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.models.EggGroup
import javax.persistence.*

@Entity
@Table(name = "egg_groups")
data class EggGroupEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val name: String
)

fun EggGroupEntity.toDomain(): EggGroup = EggGroup(
        id = this.id,
        name = this.name
)
