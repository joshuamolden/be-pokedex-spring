package com.bushelpowered.pokedex.pokedexapi.persistence.entities

import com.bushelpowered.pokedex.pokedexapi.domain.models.Trainer
import javax.persistence.*

@Entity
@Table(name = "trainer")
data class TrainerEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int? = null,
        @Column(unique = true)
        val name: String,
        @Column(unique = true)
        val email: String,
        val password: String
)

fun TrainerEntity.toDomain(): Trainer = Trainer(
        id = this.id,
        name = this.name,
        email = this.email,
        password = this.password
)