package com.bushelpowered.pokedex.pokedexapi.persistence.repoitories

import com.bushelpowered.pokedex.pokedexapi.domain.models.Ability
import com.bushelpowered.pokedex.pokedexapi.domain.models.toEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.AbilityDao
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import org.springframework.stereotype.Repository

@Repository
class AbilityRepository(val dao: AbilityDao) {

    fun findByName(name: String): Ability? {
        return dao.findByName(name)?.toDomain()
    }

    fun save(ability: Ability): Ability {
        return dao.save(ability.toEntity()).toDomain()
    }
}