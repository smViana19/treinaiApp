package br.com.samuel.treinaiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.data.repository.ExerciseRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class WorkoutDetailsViewModel @Inject constructor(
  private val repository: ExerciseRepository
) : ViewModel() {

  private val _exercises = MutableLiveData<List<ExerciseModel>>()
  val exercises: LiveData<List<ExerciseModel>> = _exercises

  fun getAllExercisesByWorkoutId(workoutId: Int) {
    viewModelScope.launch {
      try {
        //PROVISIORIO ATE EU COLOCAR A PROXIMA TELA CORRETAMENTE
        val exercises = repository.getAllExercisesByWorkoutId(workoutId)
        _exercises.postValue(exercises)
        println("exercises $_exercises e $exercises")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
  //TODO: CHAMAR A FUNCAO PARA PEGAR OS EXERCICIOS DO TREINO
}