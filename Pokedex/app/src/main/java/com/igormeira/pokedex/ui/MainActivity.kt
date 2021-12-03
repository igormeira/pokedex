package com.igormeira.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.igormeira.pokedex.R
import com.igormeira.pokedex.di.homeModule
import com.igormeira.pokedex.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class MainActivity : AppCompatActivity() {

    private val homeModules by lazy {
        unloadKoinModules(homeModule)
        loadKoinModules(homeModule)
    }

    private fun injectModules() = homeModules

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectModules()

        setUpStates()
        loadContent()
    }

    private fun setUpStates() {
        lifecycleScope.launchWhenStarted {
            homeViewModel.state.collect { state ->
                when (state) {
                    is HomeViewModel.UiState.Loading -> {}
                    is HomeViewModel.UiState.Success -> {}
                    is HomeViewModel.UiState.Error -> {}
                    else -> Unit
                }
            }
        }
    }

    private fun loadContent() {
        homeViewModel.getAllPokemons()
    }
}