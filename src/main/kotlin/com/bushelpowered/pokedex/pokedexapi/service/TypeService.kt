package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.TypeRepository
import org.springframework.stereotype.Service

@Service
class TypeService(val typeRepository: TypeRepository) {

    fun createTypeEntity(typeEntity: TypeEntity) : TypeEntity {
        return typeRepository.save(typeEntity)
    }

    fun checkType(type: TypeEntity) : TypeEntity {
        return typeRepository.findByName(type.name) ?: typeRepository.save(type)
    }
}