package br.com.samuel.treinaiapp.data.repository

import br.com.samuel.treinaiapp.data.local.converter.Converters
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseDao
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseWithLogs
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
  private val apiExerciseService: ApiExerciseService,
  private val exerciseDao: ExerciseDao,
  private val converters: Converters
) {
  suspend fun getExercisesFromApi(limit: Int, offset: Int): List<ExerciseModel> {
    val response = apiExerciseService.getExercises(limit = limit, offset = offset)
    val exercises = response.results.map { converters.fromApiToStorage(it) }
    exerciseDao.bulkInsertExercises(exercises)
    return exercises
  }

  suspend fun getExercisesFromDatabase(): List<ExerciseModel> {
    return exerciseDao.getAllExercises()
  }

  suspend fun insertExercise(exercise: ExerciseModel): Long {
    return exerciseDao.insertExercise(exercise)
  }

  suspend fun getAllExercisesAndLogs(workoutId: Int): List<ExerciseWithLogs> {
    return exerciseDao.getAllExercisesAndLogs(workoutId)
  }

  suspend fun getExerciseAndLogsById(exerciseId: Int): List<ExerciseWithLogs> {
    return exerciseDao.getExerciseAndLogsById(exerciseId)
  }

}