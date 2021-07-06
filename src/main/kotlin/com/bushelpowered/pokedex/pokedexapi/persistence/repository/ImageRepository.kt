package com.bushelpowered.pokedex.pokedexapi.persistence.repository

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Transactional
@Repository
interface ImageRepository : JpaRepository<PokemonEntity, Int> {
    @Modifying
    @Query("update PokemonEntity p set p.image = :image where p.id = :id")
    fun updateImage(@Param("image") image: String, @Param("id") id: Int)
}