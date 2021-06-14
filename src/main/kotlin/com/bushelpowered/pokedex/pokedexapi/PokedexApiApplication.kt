package com.bushelpowered.pokedex.pokedexapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class PokedexApiApplication {
	@GetMapping
	fun hello(): String {
		return "Hello world!"
	}
}

fun main(args: Array<String>) {
	runApplication<PokedexApiApplication>(*args)
}
