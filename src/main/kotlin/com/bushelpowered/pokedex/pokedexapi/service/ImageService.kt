package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.repository.ImageRepository
import com.bushelpowered.pokedex.pokedexapi.persistence.repository.PokemonRepository
import org.springframework.stereotype.Service

@Service
class ImageService(val pokemonRepository: PokemonRepository,
                   val imageRepository: ImageRepository) {

    fun addImages(): Boolean {
        var counter = 1
        while (pokemonRepository.getById(counter) != null) {
            var image = "https://intern-pokedex.myriadapps.com/images/pokemon/${counter}.png"
            imageRepository.updateImage(image, counter++)
        }
        return counter == 554
    }
}