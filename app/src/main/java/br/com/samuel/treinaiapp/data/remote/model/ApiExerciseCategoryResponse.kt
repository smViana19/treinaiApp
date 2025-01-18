package br.com.samuel.treinaiapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiExerciseCategoryResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<ApiResultCategoryResponse>
)

data class ApiResultCategoryResponse(
  var id: Int,
  val name: String
)