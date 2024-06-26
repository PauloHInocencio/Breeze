import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlinxSerialization)
}



android {
    namespace = "br.com.noartcode.breeze.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "br.com.noartcode.breeze.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            val key:String = gradleLocalProperties(rootDir, providers).getProperty("API_ACCESS_KEY")
            buildConfigField("String", "API_ACCESS_KEY", key)
        }

        getByName("debug") {
            val key:String = gradleLocalProperties(rootDir, providers).getProperty("API_ACCESS_KEY")
            buildConfigField("String", "API_ACCESS_KEY", key)
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(projects.shared)

    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.material.iconsExtended)
    implementation(libs.compose.animation)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp3)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp3.logging.interceptor)

    //Kotlinx Serialization
    implementation(libs.kotlinx.serialization)

    // Room
    implementation(libs.room)
    implementation(libs.room.coroutines)
    kapt(libs.room.compiler)
    testImplementation(libs.room.testing)


    // Coil
    implementation(libs.coil.kt.compose)


    // Coroutine Test
    testImplementation(libs.kotlinx.coroutines.test)

    // Turbine
    testImplementation(libs.turbine)

    // JUnit
    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit4.compose)

    // Hilt (for testing)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler)

    // AndroidJUnitRunner and JUnit Rules
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.rules)

}