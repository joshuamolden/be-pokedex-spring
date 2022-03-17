package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.AbilityResponse
import com.bushelpowered.pokedex.pokedexapi.domain.models.Ability
import com.bushelpowered.pokedex.pokedexapi.domain.models.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.AbilityRepository
import org.springframework.stereotype.Service

@Service
class AbilityService(val abilityRepository: AbilityRepository) {

    fun getOrAddAbility(ability: Ability): AbilityResponse {
        return abilityRepository.findByName(ability.name)?.toResponse()
                ?: abilityRepository.save(ability).toResponse()
    }
}
