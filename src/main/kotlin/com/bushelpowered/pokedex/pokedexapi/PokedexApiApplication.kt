package com.bushelpowered.pokedex.pokedexapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PokedexApiApplication

fun main(args: Array<String>) {
	runApplication<PokedexApiApplication>(*args)
}
