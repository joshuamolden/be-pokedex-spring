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

   @PostMapping("/import")
   fun testImport(): ResponseEntity<Boolean> {
       val result = csvService.importPokemon(ObjectMapper())
       return ResponseEntity.ok().body(result)
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
        val httpCode = if (returnPokemon!!.equals(null)) HttpStatus.OK else HttpStatus.NOT_FOUND
//        val httpCode = when(returnPokemon) {
//           null -> HttpStatus.NOT_FOUND
//           else -> HttpStatus.OK
//        }
        return ResponseEntity<PokemonDetailsResponse>(returnPokemon?.toDomain()?.toDetailsResponse(), httpCode)
    }
}