package br.com.samuel.treinaiapp.factory

import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel

object WorkoutFactory {
  val list = listOf(
    WorkoutModel(
      1,
      "Biceps",
      "Treino de braço"
    ),
    WorkoutModel(
      2,
      "Costas",
      "Treino de costas"
    ),
    WorkoutModel(
      3,
      "Supino",
      "Treino de peito"
    ),
  )
}