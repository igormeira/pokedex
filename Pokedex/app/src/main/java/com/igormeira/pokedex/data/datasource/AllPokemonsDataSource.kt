package com.igormeira.pokedex.data.datasource

import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.Resource
import com.igormeira.pokedex.data.model.request.PageRequest
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse

interface AllPokemonsDataSource {

    suspend fun getAllPokemons(pageRequest: PageRequest): Resource<Failure, AllPokemonsResponse>

}