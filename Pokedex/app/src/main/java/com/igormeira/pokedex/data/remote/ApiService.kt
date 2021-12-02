package com.igormeira.pokedex.data.remote

import com.igormeira.pokedex.core.Either
import com.igormeira.pokedex.core.EitherCallAdapterFactory
import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import com.squareup.moshi.Moshi
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    suspend fun getPokemons(): AllPokemonsResponse?

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) { level = LogLevel.ALL }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }

//    //region [All Pokemon]
//    @GET("pokemon/")
//    suspend fun getPokemons(
//        @Query("offset") offset: Int,
//        @Query("limit") limit: Int
//    ): Either<Failure, AllPokemonsResponse>
//
//    @GET("pokemon/?offset=0&limit=20")
//    suspend fun getPokemons2(): Either<Failure, AllPokemonsResponse>
//    //endregion
//
//    companion object {
//
//        fun getBaseService(moshi: Moshi, client: OkHttpClient): ApiService {
//            val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl("https://pokeapi.co/api/v2/")
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .addCallAdapterFactory(EitherCallAdapterFactory())
//                .client(client)
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//
//    }

}