package com.bushelpowered.pokedex.pokedexapi.controller

import com.bushelpowered.pokedex.pokedexapi.domain.*
import com.bushelpowered.pokedex.pokedexapi.service.PokemonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// @RestController                  Will be used when sending responses
// @RequestMapping("/api")          Used to create base URI
@Controller
class PokemonConrtoller {

    @Autowired
    lateinit var pokemonService: PokemonService

    @GetMapping ("/test")
    fun TestCreate() {
//        return Pokemon(1, "Bulbasaur", Type("poison", "grass"), 7.00, 69.00,
//                Ability("chlorophyll", "overgrow"), EggGroup("plant", "monster"),
//                Stats(45, 45, 49, 49, 65, 65),
//                "Seed Pokémon", "Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun’s rays, the seed grows progressively larger."
//                )
    }

//    @GetMapping("/v1/pokemon/{pokemon_id}")       url for pokemon based on id

//    @GetMapping("/v1/pokemon/{?name}")            url for pokemon based on search value
}