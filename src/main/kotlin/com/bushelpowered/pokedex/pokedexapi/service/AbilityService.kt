package com.bushelpowered.pokedex.pokedexapi.service;

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity;
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.AbilityRepository;
import org.springframework.stereotype.Service;

@Service
class AbilityService(val abilityRepository: AbilityRepository) {

    fun createAbilityEntity(ability: AbilityEntity) : AbilityEntity {
        return abilityRepository.save(ability)
    }

    fun checkAbility(ability: AbilityEntity) : AbilityEntity {
        return abilityRepository.findByName(ability.name) ?: abilityRepository.save(ability)
    }
}
