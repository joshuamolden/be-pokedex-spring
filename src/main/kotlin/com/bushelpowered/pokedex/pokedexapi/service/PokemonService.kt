package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.Pokemon
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.*
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.PokemonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Service

@Service
class PokemonService() {

    @Autowired
    lateinit var pokemonRepository: PokemonRepository

    @Autowired
    lateinit var abilityService: AbilityService

    @Autowired
    lateinit var eggGroupService: EggGroupService

    @Autowired
    lateinit var typeService: TypeService

    // create pokemon entity
    fun createPokemonEntity(pokemon: PokemonEntity) : PokemonEntity? {
        return pokemonRepository.findByName(pokemon.name) ?: pokemonRepository.save(pokemon.copy(
                types = pokemon.types.map { type -> typeService.checkType(TypeEntity(name=type.name)) },
                abilities = pokemon.abilities.map { ability -> abilityService.checkAbility(AbilityEntity(name=ability.name)) },
                egg_groups = pokemon.egg_groups.map { egg_group -> eggGroupService.checkEggGroup(EggGroupEntity(name=egg_group.name)) },
        ))
    }


    fun getAllPokemon(name: String?, pageable: Pageable): Page<Pokemon> {
        return when(name.isNullOrBlank()) {
            true -> pokemonRepository.findAll(pageable)?.map { it.toDomain() }
            false -> pokemonRepository.findByNameContaining(name, pageable)?. map { it?.toDomain() }
        }
    }

    fun getPokemonById(id: Int): PokemonEntity? {
        val result = pokemonRepository.findById(id)
        return if (result.isPresent) result.get() else null
    }

}