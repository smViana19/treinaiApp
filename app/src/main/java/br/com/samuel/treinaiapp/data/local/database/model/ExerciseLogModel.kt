package br.com.samuel.treinaiapp.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
  tableName = "exercise_logs",
  foreignKeys = [
    ForeignKey(
      entity = ExerciseModel::class,
      parentColumns = ["id"],
      childColumns = ["exercise_id"],
      onDelete = ForeignKey.CASCADE,
    )
  ]
)

data class ExerciseLogModel(
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Int = 0,
  @ColumnInfo(name = "exercise_id")
  val exerciseId: Int = 0,
  @ColumnInfo(name = "sets")
  val sets: Int = 0,
  @ColumnInfo(name = "reps")
  val reps: Int = 0,
  @ColumnInfo(name = "weight")
  val weight: Double,
  @ColumnInfo(name = "date")
  val date: Long
)