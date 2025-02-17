package br.com.samuel.treinaiapp.data.repository

import br.com.samuel.treinaiapp.data.local.database.dao.WorkoutDao
import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class WorkoutRepository @Inject constructor(
  private val workoutDao: WorkoutDao
) {
  suspend fun getAllWorkouts(): List<WorkoutModel> {
    val workouts = workoutDao.getAllWorkouts()
    return workouts
  }
  suspend fun insertWorkouts(name: String, description: String?) {
    val workout = WorkoutModel(name = name, description = description)
    workoutDao.insertWorkout(workout)
  }
  suspend fun updateWorkout() {}
  suspend fun deleteWorkout() {}
}