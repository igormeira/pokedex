package com.igormeira.pokedex.data.model.common

import com.squareup.moshi.Json

data class PokemonBaseModel(
    @field:Json(name = "name") val name: Int,
    @field:Json(name = "url") val url: String
)
