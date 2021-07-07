package com.bushelpowered.pokedex.pokedexapi.persistence.repoitories

import com.bushelpowered.pokedex.pokedexapi.domain.models.Trainer
import com.bushelpowered.pokedex.pokedexapi.domain.models.toEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.TrainerDao
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import org.springframework.stereotype.Repository

@Repository
class TrainerRepository(val dao: TrainerDao) {

    fun findByEmail(email: String): Trainer? {
        return dao.findByEmail(email)?.toDomain()
    }

    fun save(trainer: Trainer): Trainer {
        return dao.save(trainer.toEntity()).toDomain()
    }
}