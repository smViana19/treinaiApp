package br.com.samuel.treinaiapp.data.remote.api

import br.com.samuel.treinaiapp.data.remote.model.ApiExerciseCategoryResponse
import retrofit2.http.GET

interface ApiExerciseCategoryService {
  @GET("exercisecategory")
  suspend fun getExerciseCategories(): ApiExerciseCategoryResponse
}