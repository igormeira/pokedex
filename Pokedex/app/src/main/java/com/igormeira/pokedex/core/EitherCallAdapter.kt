package com.igormeira.pokedex.core

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class EitherCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<Either<Failure, R>>> {

    override fun adapt(call: Call<R>): Call<Either<Failure, R>> = EitherCall(call, successType)

    override fun responseType(): Type = successType
}