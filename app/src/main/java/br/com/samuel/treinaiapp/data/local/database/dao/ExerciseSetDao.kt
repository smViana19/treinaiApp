package br.com.samuel.treinaiapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel

@Dao
interface ExerciseSetDao {

  @Query("SELECT * FROM exercise_sets")
  suspend fun getAllExerciseSets(): List<ExerciseSetModel>

  @Insert
  suspend fun insertExerciseSets(exerciseSets: ExerciseSetModel): Long

  @Insert
  suspend fun bulkInsertExerciseSets(exerciseSets: List<ExerciseSetModel>)

  //TODO ADICIONAR O CRUD COMPLETO
}