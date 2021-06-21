package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.*
import com.bushelpowered.pokedex.pokedexapi.domain.dto.PokemonDetailsResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.PokemonListResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.service.CsvService
import com.bushelpowered.pokedex.pokedexapi.service.PokemonService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/pokemon")
class PokemonConrtoller(val pokemonService: PokemonService,
                        val csvService: CsvService) {

   @PostMapping("/import")
   fun testImport(): ResponseEntity<Boolean> {
       val result = csvService.importPokemon()
       return ResponseEntity.ok().body(result)
   }

    @GetMapping("/")
    fun loadAllPokemon(
            @RequestParam("name") name: String?,
            @PageableDefault(sort = ["name"], value = 15) pageable: Pageable
    ): ResponseEntity<Page<PokemonListResponse>> {
        return ResponseEntity.ok(pokemonService.getAllPokemon(name, pageable).map (Pokemon::toListResponse))
    }

    @GetMapping ("/test")
    fun testCreate(): Pokemon? {
        val testPokemon =  Pokemon(1, "Bulbasaur", listOf(Type("poison"), Type("grass")), 7.00, 69.00,
                listOf(Ability("chlorophyll"), Ability("overgrow")), listOf(EggGroup("plant"), EggGroup("monster")),
                Stats(45, 45, 49, 49, 65, 65),
                "Seed Pokémon", "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun’s rays, the seed grows progressively larger."
                )
        return pokemonService.createPokemonEntity(testPokemon.toEntity())?.toDomain()
    }

    @GetMapping("/{pokemon_id}")
    fun getSpecificPokemonEntity (@PathVariable pokemon_id: Int): ResponseEntity<PokemonDetailsResponse>? {
        val returnPokemon = pokemonService.getPokemonById(pokemon_id)
//        val HTTPCode = if (returnPokemon) HttpStatus.OK else HttpStatus.NOT_FOUND         can't figure out how to make this work. How can you check if returnPokemon is null?
        val httpCode = when(returnPokemon) {
           null -> HttpStatus.NOT_FOUND
           else -> HttpStatus.OK
        }
        return ResponseEntity<PokemonDetailsResponse>(returnPokemon?.toDomain()?.toDetailsResponse(), httpCode)
    }
}