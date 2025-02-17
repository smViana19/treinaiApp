package br.com.samuel.treinaiapp.repository

import br.com.samuel.treinaiapp.data.local.database.dao.WorkoutDao
import br.com.samuel.treinaiapp.data.repository.WorkoutRepository
import io.mockk.mockk

//class WorkoutRepositoryTest {
//
//  private val workoutDao = mockk<WorkoutDao>()
//  private val repository = WorkoutRepository(workoutDao)

//  @Test
//  fun getWorkouts_return_list_with_success() = runBlocking {
//    coEvery { workoutDao.getAllWorkouts() } returns WorkoutFactory.list
//    val result = repository.getAllWorkouts()
//    Assert.assertEquals(result.size, WorkoutFactory.list.size)
//  }
//}