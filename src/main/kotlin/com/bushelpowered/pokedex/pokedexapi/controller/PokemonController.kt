package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.dto.PokemonResponse
import com.bushelpowered.pokedex.pokedexapi.domain.dto.responses.PokemonListResponse
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
class PokemonController(val pokemonService: PokemonService,
                        val csvService: CsvService) {

    // imports pokemon once
    @PostMapping("/import")
    fun importFromCsv(): ResponseEntity<String> {
        var httpCode = HttpStatus.OK
        when (pokemonService.getPokemonById(1)) {
            null -> csvService.importPokemon(ObjectMapper())
            else -> httpCode = HttpStatus.BAD_REQUEST
        }
        return ResponseEntity("Pokemon already imported", httpCode)
    }

    @GetMapping("/")
    fun loadAllPokemon(
            @RequestParam("name") name: String?,
            @PageableDefault(sort = ["id"], value = 15) pageable: Pageable
    ): ResponseEntity<Page<PokemonListResponse>> {
        return ResponseEntity.ok(pokemonService.getAllPokemon(name, pageable).map { pokemonInList -> pokemonInList })
    }

    @GetMapping("/{pokemon_id}")
    fun getSpecificPokemonEntity(
            @PathVariable pokemon_id: Int
    ): ResponseEntity<PokemonResponse>? {
        val returnPokemon = pokemonService.getPokemonById(pokemon_id)
        val httpCode = when (returnPokemon) {
            null -> HttpStatus.NOT_FOUND
            else -> HttpStatus.OK
        }
        return ResponseEntity<PokemonResponse>(returnPokemon, httpCode)
    }
}