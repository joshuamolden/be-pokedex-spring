package com.bushelpowered.pokedex.pokedexapi.Persistence.Entities

import com.bushelpowered.pokedex.pokedexapi.Domain.Ability
import javax.persistence.*

@Table(name = "Ability")
@Entity
data class AbilityEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val name: String
        )

fun AbilityEntity.toDomain(): Ability = Ability (
        name = this.name
)
