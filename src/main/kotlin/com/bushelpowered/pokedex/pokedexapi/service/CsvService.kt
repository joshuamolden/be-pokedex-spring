package com.bushelpowered.pokedex.pokedexapi.service

import com.bushelpowered.pokedex.pokedexapi.persistence.entities.PokemonEntity
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

    fun importPokemon(objectMapper: ObjectMapper): Boolean {
        val csvFile = BufferedReader(FileReader("src/main/resources/csv/pokedex.csv"))

        try {
            val csvToBean: CsvToBean<PokemonCsv> = CsvToBeanBuilder<PokemonCsv>(csvFile)
                    .withType(PokemonCsv::class.java)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()

            val pokemonEntities = csvToBean.parse()

            pokemonEntities.forEach {
                pokemonService.createPokemonEntity(
                        PokemonEntity(
                                id = it.id,
                                name = it.name,
                                types = objectMapper.readValue,

                        )
                )
            }

            return true
        } catch (exception: ResponseStatusException) {
            throw exception
        } catch (exception: Exception) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "There is an issue with the uploaded CSV file")
        }
    }

    data class PokemonCsv (
            val id: Int? = null,
            val name: String = "",
            val types: String = "",
            val height: Double? = null,
            val weight: Double? = null,
            val abilities: String = "",
            val egg_groups: String = "",
            val stats: String = "",
            val genus: String? = null,
            val description: String? = null)
}
