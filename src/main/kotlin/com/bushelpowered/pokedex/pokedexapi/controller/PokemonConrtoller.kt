package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.*
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.service.PokemonService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PokemonConrtoller(val pokemonService: PokemonService) {

    @GetMapping ("/test")
    fun TestCreate(): Pokemon? {

        val testPokemon =  Pokemon(1, "Bulbasaur", listOf(Type("poison"), Type("grass")), 7.00, 69.00,
                listOf(Ability("chlorophyll"), Ability("overgrow")), listOf(EggGroup("plant"), EggGroup("monster")),
                Stats(45, 45, 49, 49, 65, 65),
                "Seed Pokémon", "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun’s rays, the seed grows progressively larger."
                )
        return pokemonService.createPokemonEntity(testPokemon.toEntity())?.toDomain()
    }

    @GetMapping("/v1/pokemon/{pokemon_id}")
    fun getSpecificPokemonEntity (
            @RequestParam("pokemon_id") pokemon_id: String
    ): ResponseEntity<PokemonEntity?>? {
        val returnPokemon = pokemonService.getSpecificPokemonEntity(pokemon_id)
        return ResponseEntity.ok().body(returnPokemon)
    }

//    @GetMapping("/v1/pokemon/{?name}")
//    fun getByNamePokemonEntity(
//            @RequestParam("name") name: String
//    ): ResponseEntity<PokemonEntity> {
//
//    }
}