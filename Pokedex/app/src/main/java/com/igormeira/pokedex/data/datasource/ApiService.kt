package com.igormeira.pokedex.data.datasource

import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //region [All Pokemon]
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Call<AllPokemonsResponse>
    //endregion

    companion object {

        fun getBaseService(): ApiService {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }

    }

}