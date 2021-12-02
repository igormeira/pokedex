package com.igormeira.pokedex.core

import com.igormeira.pokedex.core.extensions.loggerDebug

sealed class Failure {

    open class NetworkFailure(val code: Int, val message: String) : Failure() {
        init {
            this.loggerDebug()
        }
    }

    object GenericFailure: Failure()

    data class ParseFailure(val message: String): Failure()

}