package com.bushelpowered.pokedex.pokedexapi.persistence.daos

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeDao : JpaRepository<TypeEntity, Int> {
    fun findByName(name: String): TypeEntity?
}