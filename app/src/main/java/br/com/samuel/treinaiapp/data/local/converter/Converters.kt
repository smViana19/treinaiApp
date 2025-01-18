package br.com.samuel.treinaiapp.data.local.converter

import androidx.room.TypeConverter
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.data.remote.model.ApiResultExerciseResponse

class Converters {
  fun fromApiToStorage(apiResultExerciseResponse: ApiResultExerciseResponse): ExerciseModel {
    return ExerciseModel(
      id = apiResultExerciseResponse.id,
      name = apiResultExerciseResponse.name
    )
  }

  fun fromStorageToApi(exerciseModel: ExerciseModel): ApiResultExerciseResponse {
    return ApiResultExerciseResponse(
      id = exerciseModel.id ?: 0,
      name = exerciseModel.name ?: ""
    )
  }
}