package com.igormeira.pokedex.data.model.response

data class PokemonResponse(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val height: Int,
    val isDefault: Boolean,
    val order: Int,
    val weight: Int
    
)
