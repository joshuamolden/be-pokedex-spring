package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.*
import com.bushelpowered.pokedex.pokedexapi.domain.dto.PokemonResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.PokemonListResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.toDomain
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.*
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.PokemonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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

    // will check if pokemon exits already before creating pokemon
    fun createPokemon(pokemon: Pokemon) : PokemonResponse? {
        return pokemonRepository.findByName(pokemon.name)?.toDomain()?.toResponse() ?: pokemonRepository.save(pokemon.copy(
                types = pokemon.types.map { type -> typeService.getOrAddType(TypeEntity(name = type.name)).toDomain() },
                abilities = pokemon.abilities.map { ability -> abilityService.getOrAddAbility(AbilityEntity(name = ability.name)).toDomain() },
                egg_groups = pokemon.egg_groups.map { egg_group -> eggGroupService.getOrAddEggGroup(EggGroupEntity(name = egg_group.name)).toDomain() }
                ).toEntity()).toDomain().toResponse()
    }

    fun getAllPokemon(name: String?, pageable: Pageable): Page<PokemonListResponse> {
        return when(name.isNullOrBlank()) {
            true -> pokemonRepository.findAll(pageable)?.map { it.toDomain().toListResponse() }
            false -> pokemonRepository.findByNameContaining(name, pageable)?. map { it?.toDomain()?.toListResponse() }
        }
    }

    fun getPokemonById(id: Int): PokemonResponse? {
        val result = pokemonRepository.findById(id)
        return if (result.isPresent) result.get().toDomain().toResponse() else null
    }
}