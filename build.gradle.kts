// File: build.gradle.kts (Project: quoteflow)

// Registering plugins for the entire project
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false // Make sure KSP is here
}