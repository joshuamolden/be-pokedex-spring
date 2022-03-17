package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.service.CsvService
import com.bushelpowered.pokedex.pokedexapi.service.ImageService
import com.bushelpowered.pokedex.pokedexapi.service.PokemonService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/import")
class ImportController(val pokemonService: PokemonService,
                       val csvService: CsvService,
                       val imageService: ImageService) {

    // imports pokemon once from local cvs file
    @PostMapping("/pokemon")
    fun importFromCsv(): ResponseEntity<String> {
        var httpCode = HttpStatus.OK
        when (pokemonService.getPokemonById(1)) {
            null -> csvService.importPokemon(ObjectMapper())
            else -> httpCode = HttpStatus.BAD_REQUEST
        }
        return ResponseEntity("Pokemon already imported", httpCode)
    }

    // updates pokemon to contain images for frontend
    @PutMapping("/images")
    fun addImagesToPokemon(): ResponseEntity<String> {
        var httpCode = HttpStatus.OK
        when (pokemonService.getPokemonByImage("https://intern-pokedex.myriadapps.com/images/pokemon/1.png")) {
            null -> imageService.addImages()
            else -> httpCode = HttpStatus.BAD_REQUEST
        }
        return ResponseEntity("Images already added", httpCode)
    }
}