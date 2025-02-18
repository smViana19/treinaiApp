package br.com.samuel.treinaiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import br.com.samuel.treinaiapp.data.repository.ExerciseSetsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class ExerciseDetailsViewModel @Inject constructor(
  private val repository: ExerciseSetsRepository
) : ViewModel() {

  private val _exerciseSets = MutableLiveData<List<ExerciseSetModel>>()
  val exerciseSets: LiveData<List<ExerciseSetModel>> = _exerciseSets

  private var _exerciseId = 0

  fun setExerciseId(exerciseId: Int) {
    _exerciseId = exerciseId
  }

  fun loadExerciseSets() {
    viewModelScope.launch {
      val sets = repository.getSetsByExerciseId(_exerciseId)
      _exerciseSets.value = sets
    }
  }

  fun saveExerciseSets(exerciseSets: List<ExerciseSetModel>) {
    viewModelScope.launch {
      try {
        for (set in exerciseSets) {
          if (set.id == 0) {
            repository.insertExerciseSet(set)
          } else {
            repository.updateExerciseSet(set)
          }
        }
        loadExerciseSets()
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
}