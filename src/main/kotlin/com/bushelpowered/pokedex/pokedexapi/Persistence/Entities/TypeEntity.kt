package com.bushelpowered.pokedex.pokedexapi.Persistence.Entities

import com.bushelpowered.pokedex.pokedexapi.Domain.Type
import javax.persistence.*

@Table(name = "Type")
@Entity
data class TypeEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val name: String
        )

fun TypeEntity.toDomain(): Type = Type (
        name = this.name
        )