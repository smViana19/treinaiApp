package br.com.samuel.treinaiapp.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel

@Dao
interface ExerciseDao {
  @Query("SELECT * FROM exercises")
  suspend fun getAllExercises() : List<ExerciseModel>
  @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
  suspend fun bulkInsertExercises(exercise: List<ExerciseModel>)
  //TODO: ADICIONAR MAIS QUERYS
}