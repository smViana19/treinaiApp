package br.com.samuel.treinaiapp.di

import android.app.Application
import androidx.room.Room
import br.com.samuel.treinaiapp.data.local.database.AppDatabase
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseDao
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseLogsDao
import br.com.samuel.treinaiapp.data.local.database.dao.WorkoutDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides
  @Singleton
  fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "treinai_db")
      .fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
    return appDatabase.getExerciseDao()
  }

  @Provides
  fun provideWorkoutDao(appDatabase: AppDatabase): WorkoutDao {
    return appDatabase.getWorkoutDao()
  }

  @Provides
  fun provideExerciseLogsDao(appDatabase: AppDatabase): ExerciseLogsDao {
    return appDatabase.getExerciseLogsDao()
  }


}