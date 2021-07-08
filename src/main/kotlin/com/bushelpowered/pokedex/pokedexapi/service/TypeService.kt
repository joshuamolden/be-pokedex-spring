package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TypeResponse
import com.bushelpowered.pokedex.pokedexapi.domain.models.Type
import com.bushelpowered.pokedex.pokedexapi.domain.models.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.TypeRepository
import org.springframework.stereotype.Service

@Service
class TypeService(val typeRepository: TypeRepository) {

    fun getOrAddType(type: Type): TypeResponse {
        return typeRepository.findByName(type.name)?.toResponse()
                ?: typeRepository.save(type).toResponse()
    }
}