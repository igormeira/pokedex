package com.igormeira.pokedex.data.repository

import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.Resource
import com.igormeira.pokedex.data.datasource.AllPokemonsDataSource
import com.igormeira.pokedex.data.model.request.PageRequest
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import java.lang.Exception

class AllPokemonsRepositoryImpl(
    private val datasource: AllPokemonsDataSource
): AllPokemonsRepository {

    override suspend fun getAllPokemons(
        startIndex: Int, limit: Int
    ): Resource<Failure, AllPokemonsResponse> {
        return try {
            val page = PageRequest(startIndex, limit)
            datasource.getAllPokemons(page)
        } catch (error: Exception) {
            Resource.Error(Failure.GenericFailure)
        }
    }

}