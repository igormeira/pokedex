package com.igormeira.pokedex.data.datasource

import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.Resource
import com.igormeira.pokedex.data.model.request.PageRequest
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import com.igormeira.pokedex.data.remote.ApiService
import org.koin.core.KoinComponent
import org.koin.core.inject

class AllPokemonsDataSourceImpl: AllPokemonsDataSource, KoinComponent {

    private val apiService: ApiService by inject()

    override suspend fun getAllPokemons(pageRequest: PageRequest): Resource<Failure, AllPokemonsResponse> {
        return apiService.getPokemons()
    }

}
