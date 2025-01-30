package br.com.samuel.treinaiapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseDao
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseLogsDao
import br.com.samuel.treinaiapp.data.local.database.dao.WorkoutDao
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseLogModel
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel

@Database(
  entities = [ExerciseModel::class, WorkoutModel::class, ExerciseLogModel::class],
  version = 3
)
abstract class AppDatabase : RoomDatabase() {
  /**
   * WORKOUT DAO
   */
  abstract fun getWorkoutDao(): WorkoutDao

  /**
   * EXERCISES DAO
   */
  abstract fun getExerciseDao(): ExerciseDao

  /**
   * EXERCISE_LOGS DAO
   */
  abstract fun getExerciseLogsDao(): ExerciseLogsDao

}