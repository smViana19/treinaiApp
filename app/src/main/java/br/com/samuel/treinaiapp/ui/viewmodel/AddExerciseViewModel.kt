package br.com.samuel.treinaiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseModel
import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseService
import br.com.samuel.treinaiapp.data.remote.model.ApiResultExerciseResponse
import br.com.samuel.treinaiapp.data.repository.ExerciseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExerciseViewModel @Inject constructor(
  private val apiExerciseService: ApiExerciseService,
  private val exerciseRepository: ExerciseRepository
) : ViewModel() {

  private var currentPage = 1
  private val itemsPerPage = 50

  private val _exercises = MutableLiveData<List<ExerciseModel>>()
  val exercises: LiveData<List<ExerciseModel>> = _exercises



  private val _filteredExercises = MutableLiveData<List<ApiResultExerciseResponse>>()
  val filteredExercises: LiveData<List<ApiResultExerciseResponse>> = _filteredExercises

  init {
    getAndBulkInsertExercises()
  }

  fun getAndBulkInsertExercises() {
    viewModelScope.launch {
      try {
        val response = exerciseRepository.getExercisesFromApi(limit = itemsPerPage, offset = (currentPage - 1) * itemsPerPage)
        val currentList = _exercises.value.orEmpty()
        _exercises.postValue(currentList + response)
        currentPage++
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }
//  fun getExercises() {
//    viewModelScope.launch {
//      try {
//        val response = apiExerciseService.getExercises(limit = itemsPerPage, offset = (currentPage - 1) * itemsPerPage)
//        val currentList = _exercises.value.orEmpty()
//        _exercises.postValue(currentList + response.results)
//        println("exercises: $_exercises")
//        currentPage++
//      } catch (e: Exception) {
//        e.printStackTrace()
//        _exercises.postValue(emptyList())
//      }
//    }
//  }

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