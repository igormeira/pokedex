package com.igormeira.pokedex.usecase

import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.Resource
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse

interface AllPokemonUseCase {

    suspend fun executeAllPokemons(
        startIndex: Int,
        limit: Int
    ): Resource<Failure, AllPokemonsResponse>

}