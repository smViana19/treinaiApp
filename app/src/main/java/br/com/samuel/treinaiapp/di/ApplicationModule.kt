package br.com.samuel.treinaiapp.di

import android.app.Application
import androidx.room.Room
import br.com.samuel.treinaiapp.data.local.converter.Converters
import br.com.samuel.treinaiapp.data.local.database.AppDatabase
import br.com.samuel.treinaiapp.data.local.database.dao.ExerciseDao
import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseCategoryService
import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseService
import br.com.samuel.treinaiapp.data.repository.ExerciseRepository
import br.com.samuel.treinaiapp.utils.API_WGER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .build()
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .baseUrl(API_WGER)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  fun provideExerciseCategoryService(retrofit: Retrofit): ApiExerciseCategoryService {
    return retrofit.create(ApiExerciseCategoryService::class.java)
  }

  @Provides
  fun provideExerciseService(retrofit: Retrofit): ApiExerciseService {
    return retrofit.create(ApiExerciseService::class.java)
  }

  @Provides
  @Singleton
  fun provideDatabase(application: Application) : AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "treinai_db")
      .fallbackToDestructiveMigration()
//      .addCallback()
      .build()
  }

  @Provides
  fun provideExerciseDao(appDatabase: AppDatabase): ExerciseDao {
    return appDatabase.getExerciseDao()
  }
  @Provides
  @Singleton
  fun provideConverters(): Converters {
    return Converters()
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


}