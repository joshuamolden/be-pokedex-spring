package com.bushelpowered.pokedex.pokedexapi.persistence.daos

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EggGroupDao : JpaRepository<EggGroupEntity, Int> {
    fun findByName(name: String): EggGroupEntity?
}