plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.pruebascompose.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlin {
        compilerOptions {
            jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_1_8
        }
    }
    packaging {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.okhttp)
    implementation(libs.retrofit.nw)
    implementation(libs.retrofit.gson)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.adapters)
    implementation(libs.retrofit.moshi)
    implementation(libs.retrofit.scalars)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
