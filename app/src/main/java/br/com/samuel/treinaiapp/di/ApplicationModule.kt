package br.com.samuel.treinaiapp.di

import br.com.samuel.treinaiapp.data.local.converter.Converters
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseDao
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseSetDao
import br.com.samuel.treinaiapp.data.local.database.dao.WorkoutDao
import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseService
import br.com.samuel.treinaiapp.data.repository.ExerciseRepository
import br.com.samuel.treinaiapp.data.repository.ExerciseSetsRepository
import br.com.samuel.treinaiapp.data.repository.WorkoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {
  @Provides
  @Singleton
  fun provideConverters(): Converters {
    return Converters()
  }

  @Provides
  @Singleton
  fun provideWorkoutRepository(workoutDao: WorkoutDao): WorkoutRepository {
    return WorkoutRepository(workoutDao)
  }

  @Provides
  @Singleton
  fun provideExerciseRepository(
    apiExerciseService: ApiExerciseService,
    exerciseDao: ExerciseDao,
    converters: Converters
  ): ExerciseRepository {
    return ExerciseRepository(apiExerciseService, exerciseDao, converters)
  }

  @Provides
  @Singleton
  fun provideExerciseSetsRepository(
    exerciseSetDao: ExerciseSetDao
  ): ExerciseSetsRepository {
    return ExerciseSetsRepository(exerciseSetDao)
  }

}