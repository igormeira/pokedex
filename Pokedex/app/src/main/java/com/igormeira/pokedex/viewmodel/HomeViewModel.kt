package com.igormeira.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.extensions.logger
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import com.igormeira.pokedex.usecase.AllPokemonUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: AllPokemonUseCaseImpl,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    fun getAllPokemons() {
        viewModelScope.launch {
            kotlinx.coroutines.withContext(ioDispatcher) {
                return@withContext useCase.executeAllPokemons(0, 20)
            }.toString()
        }
    }

    private fun onFailure(failure: Failure) {
        logger(failure.toString())
    }

    private fun onSuccess(data: AllPokemonsResponse) {
        logger(data.toString())
    }

}