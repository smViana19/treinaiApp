package br.com.samuel.treinaiapp.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.samuel.treinaiapp.data.local.database.model.ExerciseSetModel
import br.com.samuel.treinaiapp.data.repository.ExerciseSetsRepository
import br.com.samuel.treinaiapp.ui.viewmodel.ExerciseDetailsViewModel
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
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
class ExerciseDetailsViewModelTest {

  @get:Rule
  val rule = InstantTaskExecutorRule()

  private val repository = mockk<ExerciseSetsRepository>()
  private val testDispatcher = StandardTestDispatcher()

  @Before
  fun setup() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `when viewmodel load exercise details then it should call the repository`() = runTest {
    val vm = instantiateViewModel()
    val exerciseId = 1
    val mockedList = listOf(
      ExerciseSetModel(1, 1, 8, 35.0),
      ExerciseSetModel(1, 1, 8, 35.0)
    )
    coEvery { repository.getSetsByExerciseId(exerciseId) } returns mockedList
    vm.loadExerciseSets(exerciseId)

    advanceUntilIdle()
    coVerify { repository.getSetsByExerciseId(exerciseId) }
    assert(vm.exerciseSets.value == mockedList.filter { it.exerciseId == exerciseId })
  }

  @Test
  fun `when the viewmodel receives the list of exercise details, it must save the set `() = runTest {
    val vm = instantiateViewModel()
    val newExerciseSet = ExerciseSetModel(0, 1, 8, 35.0)
    val existingExerciseSet = ExerciseSetModel(1, 1, 6, 30.0)

    val exerciseSets = listOf(newExerciseSet, existingExerciseSet)

    coEvery { repository.insertExerciseSet(newExerciseSet) } returns 100L
    coEvery { repository.updateExerciseSet(existingExerciseSet) } just Runs
    vm.saveExerciseSets(exerciseSets)
    advanceUntilIdle()

    coVerify(exactly = 1) { repository.insertExerciseSet(newExerciseSet) }
    coVerify(exactly = 1) { repository.updateExerciseSet(existingExerciseSet) }
  }


  private fun instantiateViewModel(): ExerciseDetailsViewModel {
    val vm = ExerciseDetailsViewModel(repository)
    return vm
  }

}