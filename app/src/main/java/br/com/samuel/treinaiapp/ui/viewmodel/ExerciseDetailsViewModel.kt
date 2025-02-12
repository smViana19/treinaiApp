package br.com.samuel.treinaiapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import br.com.samuel.treinaiapp.data.repository.ExerciseSetsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseDetailsViewModel @Inject constructor(
  private val repository: ExerciseSetsRepository
) : ViewModel() {

  //TODO: ISLOADING

  fun bulkInsertExerciseSets(exerciseSets: List<ExerciseSetModel>) {
    viewModelScope.launch {
      try {
        repository.bulkInsertExerciseSets(exerciseSets)
        println("Chegou aq cpa criou")
      } catch (e: Exception) {
        e.printStackTrace()
        println("Catch")
      }
    }
  }

//  fun getExerciseSets() {
//
//  }
}