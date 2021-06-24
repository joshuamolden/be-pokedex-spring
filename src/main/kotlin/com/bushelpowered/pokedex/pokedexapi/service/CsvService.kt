package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.domain.Pokemon
import com.bushelpowered.pokedex.pokedexapi.domain.Stats
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.AbilityEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.EggGroupEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.TypeEntity
import com.bushelpowered.pokedex.pokedexapi.persistence.entities.toDomain
import com.fasterxml.jackson.databind.ObjectMapper
import com.opencsv.bean.CsvToBean
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.io.BufferedReader
import java.io.FileReader

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

    fun importPokemon(objectMapper: ObjectMapper): Boolean {
        val csvFile = BufferedReader(FileReader("src/main/resources/csv/pokedex.csv"))

        try {
            val fromCsvToBean: CsvToBean<PokemonFromCsv> = CsvToBeanBuilder<PokemonFromCsv>(csvFile)
                    .withType(PokemonFromCsv::class.java)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()

            val pokemonEntities = fromCsvToBean.parse()
//            fun parseStat(statType: String, it: PokemonFromCsv): Int {
//                return jacksonObjectMapper().readTree(it.["stats"])[statType].toString().toInt()      tyring to figure out how to make helper method for stats
//            }
            // takes about 35-40 seconds to add all info to db, is this too much time?
            pokemonEntities.forEach {
                pokemonService.createPokemon(
                        Pokemon(
                                id = it.id,
                                name = it.name ?: "",
                                types = objectMapper.readTree(it.types).map { type -> TypeEntity(name = type.toString().replace("\"", "")).toDomain() },
                                height = it.height ?: 0.0,
                                weight = it.weight ?: 0.0,
                                abilities = objectMapper.readTree(it.abilities).map { ability -> AbilityEntity(name = ability.toString().replace("\"", "")).toDomain() },
                                egg_groups = objectMapper.readTree(it.egg_groups).map { egg_group -> EggGroupEntity(name = egg_group.toString().replace("\"", "")).toDomain() },
                                stats = Stats(
                                        hp = objectMapper.readTree(it.stats)["hp"].toString().toInt(),
                                        speed = objectMapper.readTree(it.stats)["speed"].toString().toInt(),
                                        attack = objectMapper.readTree(it.stats)["attack"].toString().toInt(),
                                        defense = objectMapper.readTree(it.stats)["defense"].toString().toInt(),
                                        special_attack = objectMapper.readTree(it.stats)["special-attack"].toString().toInt(),
                                        special_defense = objectMapper.readTree(it.stats)["special-defense"].toString().toInt()
                                ),
                                genus = it.genus!!,
                                description = it.description!!))
            }
            return true
        } catch (exception: ResponseStatusException) {
            throw exception
        } catch (exception: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "There is an issue with the uploaded CSV file")
        }
    }

    data class PokemonFromCsv(
            // values need to be var in order to parse from csv file
            var id: Int? = null,
            var name: String? = null,
            var types: String? = null,
            var height: Double? = null,
            var weight: Double? = null,
            var abilities: String? = null,
            var egg_groups: String? = null,
            var stats: String? = null,
            var genus: String? = null,
            var description: String? = null)
}