package br.com.samuel.treinaiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseService
import br.com.samuel.treinaiapp.data.remote.model.ApiResultExerciseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
  private val apiExerciseService: ApiExerciseService
) : ViewModel() {

  private var currentPage = 1
  private val itemsPerPage = 50

  private val _exercises = MutableLiveData<List<ApiResultExerciseResponse>>()
  val exercises: LiveData<List<ApiResultExerciseResponse>> = _exercises



  private val _filteredExercises = MutableLiveData<List<ApiResultExerciseResponse>>()
  val filteredExercises: LiveData<List<ApiResultExerciseResponse>> = _filteredExercises

  init {
    getExercises()
  }

  fun getExercises() {
    viewModelScope.launch {
      try {
        val response = apiExerciseService.getExercises(limit = itemsPerPage, offset = (currentPage - 1) * itemsPerPage)
        val currentList = _exercises.value.orEmpty()
        _exercises.postValue(currentList + response.results)
        println("exercises: $_exercises")
        currentPage++
      } catch (e: Exception) {
        e.printStackTrace()
        _exercises.postValue(emptyList())
      }
    }
  }

  fun filterExercises(query: String) {
    viewModelScope.launch {
      try {
        val response = apiExerciseService.getExercises(limit = itemsPerPage, offset = (currentPage - 1) * itemsPerPage)
        val filteredResults = response.results.filter { exercise ->
          exercise.name.contains(query, ignoreCase = true)
        }
        _filteredExercises.postValue(filteredResults)
        println("filteredExercises: ${_filteredExercises.value}")
        currentPage++
        println("currentPage: $currentPage")

      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
//  fun filterExercises(query: String) {
//
//    val currentExercises = _exercises.value ?: return
//    _filteredExercises.value = if (query.isBlank()) {
//      currentExercises
//    } else {
//      currentExercises.filter { exercise ->
//        exercise.name.contains(query, ignoreCase = true)
//      }
//    }
//  }


}