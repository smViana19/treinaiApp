package br.com.samuel.treinaiapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddExerciseViewModel @Inject constructor(
  private val exerciseRepository: ExerciseRepository
) : ViewModel() {

  fun insertExercise(exercise: ExerciseModel) {
    viewModelScope.launch {
      try {
        exerciseRepository.insertExercise(exercise)
        println("Criou o exercicio")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

}