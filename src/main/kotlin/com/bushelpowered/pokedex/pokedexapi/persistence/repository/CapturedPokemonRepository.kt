package com.bushelpowered.pokedex.pokedexapi.persistence.repository

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.CapturedPokemonEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CapturedPokemonRepository: JpaRepository<CapturedPokemonEntity, Int> {
    fun findByTrainerId(pageable: Pageable, trainerId: Int): Page<CapturedPokemonEntity?>
}