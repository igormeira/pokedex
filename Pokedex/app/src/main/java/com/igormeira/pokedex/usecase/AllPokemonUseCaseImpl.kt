package com.igormeira.pokedex.usecase

import com.igormeira.pokedex.core.Either
import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.data.model.request.PageRequest
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import com.igormeira.pokedex.data.repository.AllPokemonsRepository

class AllPokemonUseCaseImpl(
    private val allPokemonsRepository: AllPokemonsRepository
): AllPokemonUseCase {

    override suspend fun executeAllPokemons(
        startIndex: Int, limit: Int
    ): AllPokemonsResponse? =
        allPokemonsRepository.getAllPokemons(startIndex, limit)

}