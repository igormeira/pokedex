package com.igormeira.pokedex.data.repository

import com.igormeira.pokedex.core.Either
import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.map
import com.igormeira.pokedex.data.datasource.AllPokemonsDataSource
import com.igormeira.pokedex.data.model.request.PageRequest
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import java.lang.Exception

class AllPokemonsRepositoryImpl(
    private val datasource: AllPokemonsDataSource
): AllPokemonsRepository {

    override suspend fun getAllPokemons(
        startIndex: Int, limit: Int
    ): AllPokemonsResponse? {
        try {
            val page = PageRequest(startIndex, limit)
            val either = datasource.getAllPokemons(page)
            return either
        } catch (error: Exception) {
            return null
        }
    }

}