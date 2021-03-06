@Suppress("DSL_SCOPE_VIOLATION") // see: https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    alias(libs.plugins.ksp)
    id(libs.plugins.kapt.get().pluginId)

    // Dagger Hilt (https://dagger.dev/hilt/gradle-setup)
    id(libs.plugins.hilt.android.get().pluginId)
}

android {
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.github.warahiko.mus"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = libs.versions.app.versionCode.get().toInt()
        versionName = libs.versions.app.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"

                // see: https://github.com/google/oboe/blob/main/docs/GettingStarted.md
                arguments += "-DANDROID_STL=c++_shared"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("$buildDir/generated/ksp/$name/kotlin")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt")
            version = "3.18.1"
        }
    }
    buildFeatures {
        compose = true
        viewBinding = true

        // see: https://github.com/google/oboe/blob/main/docs/GettingStarted.md
        prefab = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.get()
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraintLayout)
    implementation(libs.bundles.androidx.compose)

    // Dagger Hilt (https://dagger.dev/hilt/gradle-setup)
    implementation(libs.bundles.hilt)
    kapt(libs.hilt.androidCompiler)

    // Oboe
    implementation(libs.oboe)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.testExt.junit)
    androidTestImplementation(libs.androidx.test.espressoCore)
    androidTestImplementation(libs.androidx.compose.uiTestJUnit4)

    debugImplementation(libs.androidx.compose.uiTooling)

    implementation(project(":listsealed"))
    ksp(project(":listsealed"))
}

kapt {
    // see: https://dagger.dev/hilt/gradle-setup
    correctErrorTypes = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.freeCompilerArgs += listOf(
        "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
    )
}
