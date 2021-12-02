package com.igormeira.pokedex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.igormeira.pokedex.R
import com.igormeira.pokedex.di.homeModule
import com.igormeira.pokedex.viewmodel.HomeViewModel
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

        loadContent()
    }

    private fun loadContent() {
        homeViewModel.getAllPokemons()
    }
}