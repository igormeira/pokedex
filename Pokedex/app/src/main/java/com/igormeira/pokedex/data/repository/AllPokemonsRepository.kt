package com.igormeira.pokedex.data.repository

import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.Resource
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse

interface AllPokemonsRepository {

    suspend fun getAllPokemons(startIndex: Int, limit: Int): Resource<Failure, AllPokemonsResponse>

}