package br.com.samuel.treinaiapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseDao
import br.com.samuel.treinaiapp.data.local.database.dao.WorkoutDao
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel

@Database(entities = [ExerciseModel::class, WorkoutModel::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
  abstract fun getExerciseDao(): ExerciseDao
  abstract fun getWorkoutDao(): WorkoutDao
}