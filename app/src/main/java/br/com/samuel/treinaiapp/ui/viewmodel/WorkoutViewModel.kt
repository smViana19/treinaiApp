package br.com.samuel.treinaiapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel
import br.com.samuel.treinaiapp.data.repository.WorkoutRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class WorkoutViewModel @Inject constructor(
  private val workoutRepository: WorkoutRepository
) : ViewModel() {
  private val _workouts = MutableLiveData<List<WorkoutModel>>()
  val workouts: LiveData<List<WorkoutModel>> = _workouts

  fun getWorkouts() {
    viewModelScope.launch {
      val workoutsData = workoutRepository.getWorkoutsFromLocalDatabase()
      _workouts.postValue(workoutsData)
//      _workouts.value = workoutsData
//      println("workouts: $workoutsData")
    }
  }

  fun insertWorkoutsToLocalDatabase(name: String, description: String?) {
    viewModelScope.launch {
      try {
        workoutRepository.insertWorkoutsToLocalDatabase(name, description)
        getWorkouts()
        println("CHEGOU AQ")
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }

  }

}