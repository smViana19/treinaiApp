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

  suspend fun getExercises(): List<ExerciseModel> {
    return exerciseDao.getAllExercises()
  }

  suspend fun getAllExercisesByWorkoutId(workoutId: Int): List<ExerciseModel> {
    return exerciseDao.getAllExercisesByWorkoutId(workoutId)
  }

  suspend fun insertExercise(exercise: ExerciseModel): Long {
    return exerciseDao.insertExercise(exercise)
  }


  suspend fun getAllExercisesAndLogs(workoutId: Int): List<ExerciseWithLogs> {
    return exerciseDao.getAllExercisesAndSets(workoutId)
  }

  suspend fun getExerciseAndLogsById(exerciseId: Int): List<ExerciseWithLogs> {
    return exerciseDao.getExerciseAndSetsById(exerciseId)
  }


}