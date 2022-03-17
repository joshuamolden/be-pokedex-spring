package com.bushelpowered.pokedex.pokedexapi.persistence.daos

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PokemonDao : JpaRepository<PokemonEntity, Int> {
    fun findByNameContaining(pageable: Pageable, name: String): Page<PokemonEntity?>
    fun findByName(name: String): PokemonEntity?
    fun findByImage(image: String): PokemonEntity?
}