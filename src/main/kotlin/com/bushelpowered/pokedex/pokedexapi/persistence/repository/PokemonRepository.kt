package com.bushelpowered.pokedex.pokedexapi.persistence.repository

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PokemonRepository: JpaRepository<PokemonEntity?, UUID?> {
    fun findAllByName(name: String): List<PokemonEntity?>?
    fun findAllBy(): List<PokemonEntity?>?
    fun findById(id: String): PokemonEntity?
}