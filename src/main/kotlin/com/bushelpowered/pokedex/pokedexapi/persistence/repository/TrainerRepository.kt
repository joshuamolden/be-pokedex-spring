package com.bushelpowered.pokedex.pokedexapi.persistence.repository

import com.bushelpowered.pokedex.pokedexapi.domain.models.Trainer
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TrainerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TrainerRepository : JpaRepository<TrainerEntity, Int> {
    fun findByEmail(email: String?): Trainer?
}