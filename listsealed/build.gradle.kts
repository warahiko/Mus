@Suppress("DSL_SCOPE_VIOLATION") // see: https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id(libs.plugins.kotlin.jvm.get().pluginId)
}

dependencies {
    implementation(libs.ksp.symbolProcessingApi)
}
