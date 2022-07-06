import java.util.*

val properties = Properties().apply {
    load(rootProject.file("local.properties").reader())
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Android.compileSdkVersion

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdkVersion
        targetSdk = Android.targetSdkVersion

        versionCode = 1
        versionName = "local"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        val apiKey = properties["API_KEY"]?.toString() ?: System.getenv("API_KEY")
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        buildConfigField("String", "BASE_URL", "\"https://api.m3o.com\"")
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            signingConfig = signingConfigs.getByName("debug")
            isDebuggable = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            isDebuggable = false
        }
    }

    buildFeatures {
        compose  = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0-alpha06"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Dependencies.appLibraries)
    kapt(Dependencies.kaptLibraries)
    debugImplementation (Dependencies.debugLibraries)
    testImplementation(Dependencies.testLibraries)
    androidTestImplementation(Dependencies.androidTestLibraries)
    kaptAndroidTest (Dependencies.hiltCompiler)
}
