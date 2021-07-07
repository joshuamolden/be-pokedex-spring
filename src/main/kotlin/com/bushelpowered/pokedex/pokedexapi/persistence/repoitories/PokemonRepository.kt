package com.bushelpowered.pokedex.pokedexapi.persistence.repoitories

import com.bushelpowered.pokedex.pokedexapi.domain.models.Pokemon
import com.bushelpowered.pokedex.pokedexapi.domain.models.toEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.PokemonDao
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PokemonRepository(val dao: PokemonDao) {

    fun findByNameContaining(pageable: Pageable, name: String): Page<Pokemon?> {
        return dao.findByNameContaining(pageable, name).map { it?.toDomain() }
    }

    fun findByName(name: String): Pokemon? {
        return dao.findByName(name)?.toDomain()
    }

    fun findByImage(image: String): Pokemon? {
        return dao.findByImage(image)?.toDomain()
    }

    fun findById(id: Int): Pokemon? {
        return dao.findById(id).get().toDomain()
    }

    fun save(pokemon: Pokemon): Pokemon {
        return dao.save(pokemon.toEntity()).toDomain()
    }

    fun findAll(pageable: Pageable): Page<Pokemon> {
        return dao.findAll(pageable).map { it.toDomain() }
    }

    fun getById(id: Int): Pokemon? {
        return dao.getById(id).toDomain()
    }
}