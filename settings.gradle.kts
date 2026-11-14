// Where the plugins (Kotlin Multiplatform, serialization) and their own dependencies come from.
// mavenCentral() is needed as well: the Kotlin plugin's dependencies live there, and the Plugin
// Portal no longer reliably proxies them (CI failed on a clean cache, 2026-11-14).
pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "QED-Api-Contract"

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
