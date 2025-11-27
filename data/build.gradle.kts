plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.javalenciab90.data"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }

        // Required for KSP stability
        jvmToolchain(17)
    }
}

dependencies {

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // --- Room (exclude old IntelliJ annotations) ---
    implementation(libs.room.runtime) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.room.ktx) {
        exclude(group = "com.intellij", module = "annotations")
    }
    ksp(libs.room.compiler) {
        exclude(group = "com.intellij", module = "annotations")
    }

    // Retrofit
    implementation(libs.square.retrofit2)
    implementation(libs.square.converter.retrofit2)
    implementation(libs.square.okhttp3)
    implementation(libs.square.okhttp3.loggingInterceptor)
    implementation(libs.square.converter.kotlinxSerialization)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}