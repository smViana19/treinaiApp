package br.com.samuel.treinaiapp.data.remote.api

import br.com.samuel.treinaiapp.data.remote.model.ApiExerciseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiExerciseService {
  @GET("exercise")
  suspend fun getExercises(
    @Query("limit") limit: Int = 20,
    @Query("offset") offset: Int = 0
  ): ApiExerciseResponse
}