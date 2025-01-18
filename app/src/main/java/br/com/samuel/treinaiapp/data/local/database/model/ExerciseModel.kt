package br.com.samuel.treinaiapp.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class ExerciseModel (
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Int? = null,
  @ColumnInfo(name = "name")
  val name: String? = null,
//  val description: String? = null,
//  val category: Int? = null

)