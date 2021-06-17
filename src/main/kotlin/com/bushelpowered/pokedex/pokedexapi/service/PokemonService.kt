package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.PokemonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PokemonService(val pokemonRepository: PokemonRepository) {

    @Autowired
    lateinit var abilityService: AbilityService

    @Autowired
    lateinit var eggGroupService: EggGroupService

    @Autowired
    lateinit var typeService: TypeService

    // create pokemon entity
    fun createPokemonEntity(pokemon: PokemonEntity) : PokemonEntity? {
        return pokemonRepository.findFirstByName(pokemon.name) ?: pokemonRepository.save(pokemon.copy(
                types = pokemon.types.map { type -> typeService.checkType(TypeEntity(name=type.name)) },
                abilities = pokemon.abilities.map { ability -> abilityService.checkAbility(AbilityEntity(name=ability.name)) },
                egg_groups = pokemon.egg_groups.map { egg_group -> eggGroupService.checkEggGroup(EggGroupEntity(name=egg_group.name)) },
//                stats = pokemonEntity.stats.copy()
        ))
    }

    // update pokemon entity
    fun updatePokemonEntity(pokemonEntity: PokemonEntity) : PokemonEntity? {
        return pokemonRepository.save(pokemonEntity)
    }

    // getAll
    fun getAllPokemonEntities(): List<PokemonEntity?>? {
        return pokemonRepository.findAllBy()
    }

    // getById
    fun getSpecificPokemonEntity(id: String): PokemonEntity? {
        return pokemonRepository.findById(id)
    }

}