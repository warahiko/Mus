// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // see: https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

buildscript {
    dependencies {
        // see: https://kotlinlang.org/docs/ksp-quickstart.html#create-a-processor-of-your-own
        classpath(libs.kotlin.gradle)

        // Dagger Hilt (https://dagger.dev/hilt/gradle-setup)
        classpath(libs.hilt.androidGradle)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    val ktlint by configurations.creating

    dependencies {
        ktlint(rootProject.libs.ktlint) {
            attributes {
                attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
            }
        }
    }

    val outputDir = "${project.buildDir}/reports/ktlint/"
    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

    val ktlintCheck by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)

        description = "Check Kotlin code style."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args = listOf(
            "--android",
            "--color",
            "--reporter=plain",
            "--reporter=checkstyle,output=$buildDir/reports/ktlint/ktlint-results.xml",
            "src/**/*.kt",
        )
        isIgnoreExitValue = true
    }

    val ktlintFormat by tasks.creating(JavaExec::class) {
        inputs.files(inputFiles)
        outputs.dir(outputDir)

        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        mainClass.set("com.pinterest.ktlint.Main")
        args = listOf("-F", "--android", "src/**/*.kt")
        isIgnoreExitValue = true
    }
}
