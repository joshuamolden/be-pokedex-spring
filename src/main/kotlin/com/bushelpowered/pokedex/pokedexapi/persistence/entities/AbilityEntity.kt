package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.models.Ability
import javax.persistence.*

@Entity
@Table(name = "abilities")
data class AbilityEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val name: String
)

fun AbilityEntity.toDomain(): Ability = Ability(
        id = this.id,
        name = this.name
)