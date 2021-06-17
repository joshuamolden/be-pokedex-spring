package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.EggGroupRepository
import org.springframework.stereotype.Service

@Service
class EggGroupService(val eggGroupRepository: EggGroupRepository) {

    fun createEggGroupEntity(eggGroupEntity: EggGroupEntity) : EggGroupEntity {
        return eggGroupRepository.save(eggGroupEntity)
    }

    fun checkEggGroup(eggGroup: EggGroupEntity) : EggGroupEntity {
        return eggGroupRepository.findFirstByName(eggGroup.name) ?: eggGroupRepository.save(eggGroup)
    }
}