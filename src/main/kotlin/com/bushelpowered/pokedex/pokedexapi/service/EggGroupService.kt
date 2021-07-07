package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.models.EggGroup
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.EggGroupRepository
import org.springframework.stereotype.Service

@Service
class EggGroupService(val eggGroupRepository: EggGroupRepository) {

    fun getOrAddEggGroup(eggGroup: EggGroup): EggGroup {
        return eggGroupRepository.findByName(eggGroup.name)
                ?: eggGroupRepository.save(eggGroup)
    }
}