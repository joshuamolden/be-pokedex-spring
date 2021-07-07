package com.bushelpowered.pokedex.pokedexapi.persistence.repoitories

import com.bushelpowered.pokedex.pokedexapi.domain.models.Type
import com.bushelpowered.pokedex.pokedexapi.domain.models.toEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.TypeDao
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import org.springframework.stereotype.Repository

@Repository
class TypeRepository(val dao: TypeDao) {

    fun findByName(name: String): Type? {
        return dao.findByName(name)?.toDomain()
    }

    fun save(type: Type): Type {
        return dao.save(type.toEntity()).toDomain()
    }
}