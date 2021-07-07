package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.TypeResponse
import com.bushelpowered.pokedex.pokedexapi.domain.models.Type
import com.bushelpowered.pokedex.pokedexapi.domain.models.toResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.daos.TypeDao
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.TypeRepository
import org.springframework.stereotype.Service

@Service
class TypeService(val typeRepository: TypeRepository) {

    fun getOrAddType(type: Type): Type {
        return typeRepository.findByName(type.name)
                ?: typeRepository.save(type)
    }
}