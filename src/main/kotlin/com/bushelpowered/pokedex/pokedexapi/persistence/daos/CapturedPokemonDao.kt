package com.bushelpowered.pokedex.pokedexapi.persistence.daos

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.CapturedPokemonEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CapturedPokemonDao : JpaRepository<CapturedPokemonEntity, Int> {
    fun findByTrainerId(pageable: Pageable, trainerId: Int): Page<CapturedPokemonEntity?>

    @Query("select c from CapturedPokemonEntity c where c.trainer.id = :trainerId and c.pokemon.id = :pokemonId")
    fun checkIfCaptured(@Param("trainerId") trainerId: Int, @Param("pokemonId") pokemonId: Int): CapturedPokemonEntity?
}