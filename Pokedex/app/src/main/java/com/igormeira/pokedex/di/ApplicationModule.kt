package com.igormeira.pokedex.di

import android.app.Application
import com.igormeira.pokedex.data.datasource.AllPokemonsDataSource
import com.igormeira.pokedex.data.datasource.AllPokemonsDataSourceImpl
import com.igormeira.pokedex.data.remote.ApiService
import com.igormeira.pokedex.data.repository.AllPokemonsRepository
import com.igormeira.pokedex.data.repository.AllPokemonsRepositoryImpl
import com.igormeira.pokedex.usecase.AllPokemonUseCaseImpl
import com.igormeira.pokedex.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val serviceModule = module {
    single { ApiService.create() }
}

val homeModule = module {
    single<AllPokemonsRepository> { AllPokemonsRepositoryImpl(get()) }
    single<AllPokemonsDataSource> { AllPokemonsDataSourceImpl() }

    viewModel { HomeViewModel(get(), Dispatchers.IO) }

    factory { AllPokemonUseCaseImpl(get()) }
}

class ApplicationModule : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationModule)
            modules(listOf(serviceModule, homeModule))
        }
    }
}