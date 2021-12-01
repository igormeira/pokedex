package com.igormeira.pokedex.data.model.request

data class PageRequest(
    val startIndex: Int,
    val sizeLimit: Int
)
