package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.*
import com.bushelpowered.pokedex.pokedexapi.domain.dto.PokemonDetailsResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.PokemonListResponse
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.bushelpowered.pokedex.pokedexapi.service.CsvService
import com.bushelpowered.pokedex.pokedexapi.service.PokemonService
import com.fasterxml.jackson.databind.ObjectMapper
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

    // imports pokemon once
   @PostMapping("/import")
   fun testImport(): ResponseEntity<String> {
       var bodyInfo = "Pokemon Imported"
       when(pokemonService.getPokemonById(1)) {
           null ->  csvService.importPokemon(ObjectMapper())
           else -> bodyInfo = "Pokemon Already imported"
       }
       return ResponseEntity.ok().body(bodyInfo)
   }

    @GetMapping("/")
    fun loadAllPokemon(
            @RequestParam("name") name: String?,
            @PageableDefault(sort = ["id"], value = 15) pageable: Pageable
    ): ResponseEntity<Page<PokemonListResponse>> {
        return ResponseEntity.ok(pokemonService.getAllPokemon(name, pageable).map (Pokemon::toListResponse))
    }

    @GetMapping("/{pokemon_id}")
    fun getSpecificPokemonEntity (@PathVariable pokemon_id: Int): ResponseEntity<PokemonDetailsResponse>? {
        val returnPokemon = pokemonService.getPokemonById(pokemon_id)
//        val httpCode = if (returnPokemon!!.equals(null)) HttpStatus.OK else HttpStatus.NOT_FOUND      can't figure out how to make if function like the when block of code
        val httpCode = when(returnPokemon) {
           null -> HttpStatus.NOT_FOUND
           else -> HttpStatus.OK
        }
        return ResponseEntity<PokemonDetailsResponse>(returnPokemon?.toDomain()?.toDetailsResponse(), httpCode)
    }
}