package br.com.samuel.treinaiapp.data.repository

import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseLogsDao
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseLogModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseLogsRepository @Inject constructor(
  private val exerciseLogsDao: ExerciseLogsDao
) {
  suspend fun getAllExercisesLogs(): List<ExerciseLogModel> {
    return exerciseLogsDao.getAllExerciseLogs()
  }

  suspend fun insertExerciseLogs(exerciseLogs: ExerciseLogModel): Long {
    return exerciseLogsDao.insertExerciseLogs(exerciseLogs)
  }

  suspend fun getAllLogsByExerciseId(exerciseId: Int): List<ExerciseLogModel> {
    return exerciseLogsDao.getAllLogsByExerciseId(exerciseId)
  }
}