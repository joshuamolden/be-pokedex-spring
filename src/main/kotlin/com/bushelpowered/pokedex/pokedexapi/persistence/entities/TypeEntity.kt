package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.models.Type
import javax.persistence.*

@Entity
@Table(name = "types")
data class TypeEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val name: String
)

fun TypeEntity.toDomain(): Type = Type(
        id = this.id,
        name = this.name
)