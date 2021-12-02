package com.igormeira.pokedex.core

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.UnsupportedOperationException
import java.lang.reflect.Type

internal class EitherCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<Either<Failure, R>> {

    override fun enqueue(callback: Callback<Either<Failure, R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@EitherCall, Response.success(response.toEither()))
            }

            private fun Response<R>.toEither(): Either<Failure, R> {
                // Http error response (4xx - 5xx)
                if (!isSuccessful) {
                    val errorBody = errorBody()?.string() ?: ""
                    return Either.Left(Failure.NetworkFailure(code(), errorBody))
                }

                // Http success response with body
                body()?.let { body -> return Either.Right(body) }

                // if we defined Unit as success type it means we expected no response body
                // e.g. in case of 204 No Content
                return if (successType == Unit::class.java) {
                    @Suppress("UNCHECKED_CAST")
                    Either.Right(Unit) as Either<Failure, R>
                } else {
                    @Suppress("UNCHECKED_CAST")
                    Either.Left(UnknownError("Response body was null")) as Either<Failure, R>
                }
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val error = when (throwable) {
                    is IOException -> Failure.NetworkFailure(400, throwable.message.toString())
                    else -> Failure.GenericFailure
                }
                callback.onResponse(this@EitherCall, Response.success(Either.Left(error)))
            }
        }
    )

    override fun clone(): Call<Either<Failure, R>> = EitherCall(delegate.clone(), successType)

    override fun execute(): Response<Either<Failure, R>> {
        throw UnsupportedOperationException("EitherCall does not support execute")
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}