package br.com.samuel.treinaiapp.di

import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseCategoryService
import br.com.samuel.treinaiapp.data.remote.api.ApiExerciseService
import br.com.samuel.treinaiapp.utils.API_WGER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
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


}