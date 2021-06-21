package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.EggGroupRepository
import org.springframework.stereotype.Service

@Service
class EggGroupService(val eggGroupRepository: EggGroupRepository) {

    fun checkEggGroup(eggGroup: EggGroupEntity) : EggGroupEntity {
        return eggGroupRepository.findByName(eggGroup.name) ?: eggGroupRepository.save(eggGroup)
    }
}