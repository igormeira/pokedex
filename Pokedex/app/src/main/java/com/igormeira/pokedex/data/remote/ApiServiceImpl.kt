package com.igormeira.pokedex.data.remote

import com.igormeira.pokedex.core.Failure
import com.igormeira.pokedex.core.Resource
import com.igormeira.pokedex.core.extensions.logger
import com.igormeira.pokedex.data.model.response.AllPokemonsResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import java.lang.Exception

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {

    override suspend fun getPokemons(): Resource<Failure, AllPokemonsResponse> {
        return try {
            Resource.Success(
                client.get { url(HttpRoutes.ALL_POKEMONS) }
            )
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ClientRequestException) {
            // 4xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ServerResponseException) {
            // 5xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        } catch (e: ResponseException) {
            // 6xx - responses
            Resource.Error(Failure.NetworkFailure(
                code = e.response.status.value,
                message = "Error: ${e.response.status.description}")
            )
        }
    }

//    //region [All Pokemon]
//    @GET("pokemon/")
//    suspend fun getPokemons(
//        @Query("offset") offset: Int,
//        @Query("limit") limit: Int
//    ): Either<Failure, AllPokemonsResponse>
//
//    @GET("pokemon/?offset=0&limit=20")
//    suspend fun getPokemons2(): Either<Failure, AllPokemonsResponse>
//    //endregion
//
//    companion object {
//
//        fun getBaseService(moshi: Moshi, client: OkHttpClient): ApiService {
//            val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl("https://pokeapi.co/api/v2/")
//                .addConverterFactory(MoshiConverterFactory.create(moshi))
//                .addCallAdapterFactory(EitherCallAdapterFactory())
//                .client(client)
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//
//    }

}