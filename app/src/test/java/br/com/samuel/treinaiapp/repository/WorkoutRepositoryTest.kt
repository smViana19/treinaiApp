package br.com.samuel.treinaiapp.repository

import br.com.samuel.treinaiapp.data.local.database.dao.WorkoutDao
import br.com.samuel.treinaiapp.data.repository.WorkoutRepository
import br.com.samuel.treinaiapp.factory.WorkoutFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class WorkoutRepositoryTest {

  private val workoutDao = mockk<WorkoutDao>()
  private val repository = WorkoutRepository(workoutDao)

  @Test
  fun getWorkouts_return_list_with_success() = runBlocking {
    coEvery { workoutDao.getAllWorkouts() } returns WorkoutFactory.list
    val result = repository.getWorkoutsFromLocalDatabase()
    Assert.assertEquals(result.size, WorkoutFactory.list.size)
  }
}