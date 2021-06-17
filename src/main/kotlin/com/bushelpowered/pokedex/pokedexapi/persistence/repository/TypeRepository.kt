package com.bushelpowered.pokedex.pokedexapi.persistence.repository

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TypeRepository: JpaRepository<TypeEntity, Int> {
    fun findFirstByName(name: String): TypeEntity?
}