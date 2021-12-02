package com.igormeira.pokedex.data.remote

object HttpRoutes {

    private const val BASE_URL = "https://pokeapi.co/api/v2"
    const val ALL_POKEMONS = "$BASE_URL/pokemon/?offset=0&limit=20"

}