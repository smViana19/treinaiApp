package br.com.samuel.treinaiapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseLogModel


@Dao
interface ExerciseLogsDao {
  @Query("SELECT * FROM exercise_logs")
  suspend fun getAllExerciseLogs(): List<ExerciseLogModel>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertExerciseLogs(exerciseLogs: ExerciseLogModel): Long

  @Query("SELECT * FROM exercise_logs WHERE :exerciseId = exercise_id")
  suspend fun getAllLogsByExerciseId(exerciseId: Int): List<ExerciseLogModel>
}