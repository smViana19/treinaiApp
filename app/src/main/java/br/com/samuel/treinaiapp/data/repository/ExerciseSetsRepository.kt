package br.com.samuel.treinaiapp.data.repository

import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseSetDao
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseSetsRepository @Inject constructor(
  private val exerciseSetDao: ExerciseSetDao
) {
  suspend fun getExerciseSets(): List<ExerciseSetModel> {
    return exerciseSetDao.getAllExerciseSets()
  }

  suspend fun getSetsByExerciseId(exerciseId: Int): List<ExerciseSetModel> {
    return exerciseSetDao.getSetsByExerciseId(exerciseId)
  }

  suspend fun insertExerciseSet(exerciseSet: ExerciseSetModel): Long {
    return exerciseSetDao.insertExerciseSets(exerciseSet)
  }

  suspend fun bulkInsertExerciseSets(exerciseSets: List<ExerciseSetModel>) {
    return exerciseSetDao.bulkInsertExerciseSets(exerciseSets)
  }

  suspend fun updateExerciseSet(exerciseSet: ExerciseSetModel) {
    return exerciseSetDao.updateExerciseSet(exerciseSet)
  }

}