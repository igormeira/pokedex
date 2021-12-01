package com.igormeira.pokedex.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

val serviceModule = module {
    single {  }
    single {  }
}

val homeModule = module {

}

class ApplicationModule : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationModule)
            modules(listOf(serviceModule))
        }
    }
}