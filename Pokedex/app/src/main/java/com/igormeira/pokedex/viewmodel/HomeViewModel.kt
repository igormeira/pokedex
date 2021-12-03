package com.igormeira.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.extensions.logger
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import com.igormeira.pokedex.usecase.AllPokemonUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: AllPokemonUseCaseImpl,
    private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

    private val _state = MutableStateFlow<UiState>(UiState.Initial)
    val state: StateFlow<UiState> = _state

    fun getAllPokemons() {
        _state.value = UiState.Loading
        viewModelScope.launch {
            kotlinx.coroutines.withContext(ioDispatcher) {
                return@withContext useCase.executeAllPokemons(0, 20)
            }.fold(::onFailure, ::onSuccess)
        }
    }

    private fun onFailure(failure: Failure) {
        logger(failure.toString())
        val uiState = UiState.Error(failure)
        _state.value = uiState
    }

    private fun onSuccess(data: AllPokemonsResponse) {
        logger(data.results.toString())
        val uiState = UiState.Success(data)
        _state.value = uiState
    }

    sealed class UiState {
        object Initial : UiState()
        object Loading : UiState()
        data class Error(val error: Failure) : UiState()
        data class Success(val pages: AllPokemonsResponse) : UiState()
    }
}