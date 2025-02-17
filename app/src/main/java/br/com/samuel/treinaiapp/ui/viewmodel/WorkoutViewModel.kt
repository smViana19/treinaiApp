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

  fun getAllWorkouts() {
    viewModelScope.launch {
      val workoutsData = workoutRepository.getAllWorkouts()
      _workouts.postValue(workoutsData)
    }
  }

  fun insertWorkouts(name: String, description: String?) {
    viewModelScope.launch {
      try {
        workoutRepository.insertWorkouts(name, description)
        getAllWorkouts()
      } catch (e: Exception) {
        e.printStackTrace()
      }
    }
  }

}