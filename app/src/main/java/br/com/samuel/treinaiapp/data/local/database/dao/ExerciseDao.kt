package br.com.samuel.treinaiapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel

@Dao
interface ExerciseDao {
  @Query("SELECT * FROM exercises")
  suspend fun getAllExercises(): List<ExerciseModel>

  @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
  suspend fun bulkInsertExercises(exercise: List<ExerciseModel>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertExercise(exercise: ExerciseModel): Long

  @Query(
    """
        SELECT 
            exercises.id AS exerciseId,
            exercises.name AS exerciseName,
            exercise_logs.sets AS sets,
            exercise_logs.reps AS reps,
            exercise_logs.weight AS weight,
            exercise_logs.date AS date
        FROM exercises
        INNER JOIN exercise_logs ON exercises.id = exercise_logs.exercise_id
        WHERE exercises.workout_id = :workoutId
    """
  )
  suspend fun getAllExercisesAndLogs(workoutId: Int): List<ExerciseWithLogs>

  @Query(
    """
        SELECT 
            exercises.id AS exerciseId,
            exercises.name AS exerciseName,
            exercise_logs.sets AS sets,
            exercise_logs.reps AS reps,
            exercise_logs.weight AS weight,
            exercise_logs.date AS date
        FROM exercises
        INNER JOIN exercise_logs ON exercises.id = exercise_logs.exercise_id
        WHERE exercises.id = :exerciseId
    """
  )
  suspend fun getExerciseAndLogsById(exerciseId: Int): List<ExerciseWithLogs>


}

data class ExerciseWithLogs(
  val exerciseId: Int = 0,
  val exerciseName: String = "",
  val sets: Int = 0,
  val reps: Int = 0,
  val weight: Double = 0.0,
  val date: Long = 0L
)