package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.PokemonListResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.PokemonResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.toDomain
import com.bushelpowered.pokedex.pokedexapi.domain.models.*
import com.bushelpowered.pokedex.pokedexapi.persistence.repoitories.PokemonRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PokemonService(val pokemonRepository: PokemonRepository,
                     val abilityService: AbilityService,
                     val eggGroupService: EggGroupService,
                     val typeService: TypeService) {

    // will check if pokemon exits already before creating pokemon
    fun createPokemon(pokemon: Pokemon): PokemonResponse? {
        return pokemonRepository.findByName(pokemon.name)?.toResponse()
                ?: pokemonRepository.save(pokemon.copy(
                        types = pokemon.types.map { type -> typeService.getOrAddType(Type(name = type.name)).toDomain() },
                        abilities = pokemon.abilities.map { ability -> abilityService.getOrAddAbility(Ability(name = ability.name)).toDomain() },
                        egg_groups = pokemon.egg_groups.map { egg_group -> eggGroupService.getOrAddEggGroup(EggGroup(name = egg_group.name)).toDomain() }
                )).toResponse()
    }

    fun getAllPokemon(name: String?, pageable: Pageable): Page<PokemonListResponse> {
        return when (name.isNullOrBlank()) {
            true -> pokemonRepository.findAll(pageable).map { it.toListResponse() }
            false -> pokemonRepository.findByNameContaining(pageable, name).map { it?.toListResponse() }
        }
    }

    fun getPokemonById(id: Int): PokemonResponse? {
        val result = pokemonRepository.findById(id)
        return result?.toResponse()
    }

    fun getPokemonByImage(image: String): String? {
        val result = pokemonRepository.findByImage(image)
        return result?.image
    }
}