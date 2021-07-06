package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.service.CsvService
import com.bushelpowered.pokedex.pokedexapi.service.PokemonService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/import")
class ImportController(val pokemonService: PokemonService,
                       val csvService: CsvService) {

    // imports pokemon once from local cvs file
    @PostMapping("/")
    fun importFromCsv(): ResponseEntity<String> {
        var httpCode = HttpStatus.OK
        when (pokemonService.getPokemonById(1)) {
            null -> csvService.importPokemon(ObjectMapper())
            else -> httpCode = HttpStatus.BAD_REQUEST
        }
        return ResponseEntity("Pokemon already imported", httpCode)
    }
}