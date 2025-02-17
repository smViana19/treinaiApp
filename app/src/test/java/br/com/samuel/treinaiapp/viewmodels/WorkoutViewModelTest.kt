package br.com.samuel.treinaiapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.samuel.treinaiapp.data.local.database.model.WorkoutModel
import br.com.samuel.treinaiapp.data.repository.WorkoutRepository
import br.com.samuel.treinaiapp.ui.viewmodel.WorkoutViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WorkoutViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  private val repository = mockk<WorkoutRepository>()
  private val testDispatcher = StandardTestDispatcher()
  private val workoutObserver = mockk<Observer<List<WorkoutModel>>>(relaxed = true)


  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `when viewmodel fetches workouts then it should call the repository`() = runTest {
    val viewModel = instantiateViewmodel()
    val mockedList = listOf(WorkoutModel(1, "workout A", "description A"))

    coEvery { repository.getAllWorkouts() } returns mockedList

    viewModel.getAllWorkouts()

    advanceUntilIdle()
    coVerify { repository.getAllWorkouts() }
    coVerify { workoutObserver.onChanged(mockedList) }
  }

  private fun instantiateViewmodel(): WorkoutViewModel {
    val viewmodel = WorkoutViewModel(repository)
    viewmodel.workouts.observeForever(workoutObserver)
    return viewmodel
  }
}