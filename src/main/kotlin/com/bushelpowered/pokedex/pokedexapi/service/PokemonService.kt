package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.PokemonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PokemonService {

    @Autowired
    lateinit var pokemonRepository: PokemonRepository

    // create pokemon entity
    fun createPokemonEntity(pokemonEntity: PokemonEntity) : PokemonEntity? {
        return pokemonRepository.save(pokemonEntity)
    }

    // update pokemon entity
    fun updatePokemonEntity(pokemonEntity: PokemonEntity) : PokemonEntity? {
        return pokemonRepository.save(pokemonEntity)
    }

    // getAll
    fun getAllPokemonEntities(): List<PokemonEntity?>? {
        return pokemonRepository.findAllBy()
    }

    // getById
    fun getSpecificPokemonEntity(id: String): PokemonEntity? {
        return pokemonRepository.findById(id)
    }
}