package com.bushelpowered.pokedex.pokedexapi.persistence.repository

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EggGroupRepository: JpaRepository<EggGroupEntity?, UUID?> {
    fun findByName(name: String): EggGroupEntity?
}