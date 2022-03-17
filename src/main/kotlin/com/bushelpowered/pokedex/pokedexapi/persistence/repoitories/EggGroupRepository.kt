package com.bushelpowered.pokedex.pokedexapi.persistence.repoitories

import com.bushelpowered.pokedex.pokedexapi.domain.models.EggGroup
import com.bushelpowered.pokedex.pokedexapi.domain.models.toEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.EggGroupDao
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import org.springframework.stereotype.Repository

@Repository
class EggGroupRepository(val dao: EggGroupDao) {

    fun findByName(name: String): EggGroup? {
        return dao.findByName(name)?.toDomain()
    }

    fun save(eggGroup: EggGroup): EggGroup {
        return dao.save(eggGroup.toEntity()).toDomain()
    }
}