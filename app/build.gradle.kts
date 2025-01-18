plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  id ("kotlin-kapt")
  id("com.google.dagger.hilt.android")
}

android {
  namespace = "br.com.samuel.treinaiapp"
  compileSdk = 35

  defaultConfig {
    applicationId = "br.com.samuel.treinaiapp"
    minSdk = 29
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    viewBinding = true
  }
}

dependencies {


  kapt(libs.hilt.android.compiler)
  implementation(libs.hilt.android)

  implementation(libs.retrofit)
  implementation(libs.retrofit.gson.convertor)

  implementation(libs.androidx.room.runtime)

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.activity)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.annotation)
  implementation(libs.androidx.lifecycle.livedata.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
}