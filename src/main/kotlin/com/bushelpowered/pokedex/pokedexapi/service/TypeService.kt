package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TypeResponse
import com.bushelpowered.pokedex.pokedexapi.domain.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.TypeRepository
import org.springframework.stereotype.Service

@Service
class TypeService(val typeRepository: TypeRepository) {

    fun getOrAddType(type: TypeEntity): TypeResponse {
        return typeRepository.findByName(type.name)?.toDomain()?.toResponse()
                ?: typeRepository.save(type).toDomain().toResponse()
    }
}