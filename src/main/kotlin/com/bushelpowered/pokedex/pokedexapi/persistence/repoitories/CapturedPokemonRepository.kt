package com.bushelpowered.pokedex.pokedexapi.persistence.repoitories

import com.bushelpowered.pokedex.pokedexapi.domain.models.CapturedPokemon
import com.bushelpowered.pokedex.pokedexapi.domain.models.toEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.CapturedPokemonDao
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CapturedPokemonRepository(val dao: CapturedPokemonDao) {
    fun findById(pageable: Pageable, trainerId: Int): Page<CapturedPokemon?> {
        return dao.findByTrainerId(pageable, trainerId).map { it?.toDomain() }
    }

    fun checkIfCaptured(trainerId: Int, pokemonId: Int): CapturedPokemon? {
        return dao.checkIfCaptured(trainerId, pokemonId)?.toDomain()
    }

    fun save(capturedPokemon: CapturedPokemon): CapturedPokemon {
        return dao.save(capturedPokemon.toEntity()).toDomain()
    }
}