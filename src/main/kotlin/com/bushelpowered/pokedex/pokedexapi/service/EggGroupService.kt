package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.EggGroupResponse
import com.bushelpowered.pokedex.pokedexapi.domain.models.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.EggGroupRepository
import org.springframework.stereotype.Service

@Service
class EggGroupService(val eggGroupRepository: EggGroupRepository) {

    fun getOrAddEggGroup(eggGroup: EggGroupEntity): EggGroupResponse {
        return eggGroupRepository.findByName(eggGroup.name)?.toDomain()?.toResponse()
                ?: eggGroupRepository.save(eggGroup).toDomain().toResponse()
    }
}