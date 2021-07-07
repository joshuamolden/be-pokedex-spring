package com.bushelpowered.pokedex.pokedexapi.persistence.daos

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilityDao : JpaRepository<AbilityEntity, Int> {
    fun findByName(name: String): AbilityEntity?
}