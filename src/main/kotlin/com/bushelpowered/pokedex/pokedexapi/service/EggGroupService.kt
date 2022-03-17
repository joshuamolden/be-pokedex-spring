package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.EggGroupResponse
import com.bushelpowered.pokedex.pokedexapi.domain.models.EggGroup
import com.bushelpowered.pokedex.pokedexapi.domain.models.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.EggGroupRepository
import org.springframework.stereotype.Service

@Service
class EggGroupService(val eggGroupRepository: EggGroupRepository) {

    fun getOrAddEggGroup(eggGroup: EggGroup): EggGroupResponse {
        return eggGroupRepository.findByName(eggGroup.name)?.toResponse()
                ?: eggGroupRepository.save(eggGroup).toResponse()
    }
}