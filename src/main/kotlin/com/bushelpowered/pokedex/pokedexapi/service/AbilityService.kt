package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.AbilityResponse
import com.bushelpowered.pokedex.pokedexapi.domain.models.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.AbilityRepository
import org.springframework.stereotype.Service

@Service
class AbilityService(val abilityRepository: AbilityRepository) {

    fun getOrAddAbility(ability: AbilityEntity): AbilityResponse {
        return abilityRepository.findByName(ability.name)?.toDomain()?.toResponse()
                ?: abilityRepository.save(ability).toDomain().toResponse()
    }
}
