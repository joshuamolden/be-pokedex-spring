package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.Trainer
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import javax.persistence.*

@Entity
@Table(name = "trainer")
data class TrainerEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val user_name: String,
        @Column(unique = true)
        val email: String,
        @Column(unique = true)
        val password: String,
        @ManyToMany(fetch = FetchType.LAZY, mappedBy = "trainers")
        val pokemon_list: List<PokemonEntity>
        )

fun TrainerEntity.ToDomain(): Trainer = Trainer (
        user_name = this.user_name,
        email = this.email,
        password = this.password,
        pokemon_list = this.pokemon_list.map { pokemonEntity -> pokemonEntity.toDomain() }
        )