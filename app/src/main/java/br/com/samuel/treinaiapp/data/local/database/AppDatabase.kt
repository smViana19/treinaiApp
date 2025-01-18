package br.com.samuel.treinaiapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseDao
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel

@Database(entities = [ExerciseModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
  abstract fun getExerciseDao() : ExerciseDao

  //ver oque e o callback
}