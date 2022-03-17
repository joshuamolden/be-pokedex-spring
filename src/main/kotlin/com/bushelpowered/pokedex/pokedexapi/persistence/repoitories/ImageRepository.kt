package com.bushelpowered.pokedex.pokedexapi.persistence.repoitories

import com.bushelpowered.pokedex.pokedexapi.persistence.daos.ImageDao
import org.springframework.stereotype.Repository

@Repository
class ImageRepository(val dao: ImageDao) {

    fun updateImage(image: String, id: Int) {
        return dao.updateImage(image, id)
    }
}