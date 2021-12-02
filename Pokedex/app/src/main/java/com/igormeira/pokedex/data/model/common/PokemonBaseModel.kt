package com.igormeira.pokedex.data.model.common

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class PokemonBaseModel(
    val name: Int,
    val url: String
)

//data class PokemonBaseModel(
//    @field:Json(name = "name") val name: Int,
//    @field:Json(name = "url") val url: String
//)
