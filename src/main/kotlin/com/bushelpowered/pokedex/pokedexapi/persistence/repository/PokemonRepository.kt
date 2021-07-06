package com.bushelpowered.pokedex.pokedexapi.persistence.repository

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonRepository : JpaRepository<PokemonEntity, Int> {
    fun findByNameContaining(name: String, pageable: Pageable): Page<PokemonEntity?>
    fun findByName(name: String): PokemonEntity?
    fun findByImage(image: String): PokemonEntity?
}