package com.igormeira.pokedex.data.model.response

import com.igormeira.pokedex.data.model.common.PokemonBaseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@Serializable
data class AllPokemonsResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonBaseModel>
)

//@JsonClass(generateAdapter = true)
//data class AllPokemonsResponse(
//    @field:Json(name = "count") val count: Int,
//    @field:Json(name = "next") val nextPage: String?,
//    @field:Json(name = "previous") val previousPage: String?,
//    @field:Json(name = "results") val results: List<PokemonBaseModel>
//)
