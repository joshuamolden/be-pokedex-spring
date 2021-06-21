package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.io.BufferedReader
import java.io.FileReader
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@Service
class CsvService {

    @Autowired
    lateinit var pokemonService: PokemonService

    @Autowired
    lateinit var typeService: TypeService

    @Autowired
    lateinit var abilityService: AbilityService

    @Autowired
    lateinit var eggGroupService: EggGroupService

    fun importPokemon(): Boolean {
        val csvFile = BufferedReader(FileReader("src/main/resources/csv/pokedex.csv"))
        val objectMapper = jacksonObjectMapper()

        try {
            val csvToBean: CsvToBean<PokemonCsv> = CsvToBeanBuilder<PokemonCsv>(csvFile)
                    .withType(PokemonCsv::class.java)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()

            val pokemonEntities = csvToBean.parse()

            pokemonEntities.forEach {
                pokemonService.createPokemonEntity(
                        PokemonEntity(
                                id = it.id?.toInt(),
                                name = it.name,
                                types = objectMapper.readTree(it.types).map { type -> typeService.checkType(TypeEntity(name=type.toString())) },
                                height = it.height,
                                weight = it.weight,
                                abilities = objectMapper.readTree(it.abilities).map { ability -> abilityService.checkAbility(AbilityEntity(name=ability.toString())) },
                                egg_groups = objectMapper.readTree(it.egg_groups).map { egg_group -> eggGroupService.checkEggGroup(EggGroupEntity(name=egg_group.toString())) },
                                hp = objectMapper.readTree(it.stats)["hp"].toString().toInt(),
                                speed = objectMapper.readTree(it.stats)["speed"].toString().toInt(),
                                attack = objectMapper.readTree(it.stats)["attack"].toString().toInt(),
                                defense = objectMapper.readTree(it.stats)["defense"].toString().toInt(),
                                special_attack = objectMapper.readTree(it.stats)["special-attack"].toString().toInt(),
                                special_defense = objectMapper.readTree(it.stats)["special-defense"].toString().toInt(),
                                genus = it.genus,
                                description = it.description))
            }
            return true
        } catch (exception: ResponseStatusException) {
            throw exception
        } catch (exception: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "There is an issue with the uploaded CSV file")
        }
    }

    data class PokemonCsv (
            val id: String? = null,
            val name: String = "",
            val types: String = "",
            val height: Double = 0.0,
            val weight: Double = 0.0,
            val abilities: String = "",
            val egg_groups: String = "",
            val stats: String = "",
            val genus: String = "",
            val description: String = "")
}