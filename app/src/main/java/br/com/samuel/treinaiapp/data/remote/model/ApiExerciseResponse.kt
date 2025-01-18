package br.com.samuel.treinaiapp.data.remote.model

data class ApiExerciseResponse(
  val count: Int,
  val next: String?,
  val previous: String?,
  val results: List<ApiResultExerciseResponse>
)

data class ApiResultExerciseResponse(
  val id: Int,
  val name: String,
//  val description: String,
//  val category: Int,
)