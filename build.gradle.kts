// QED-Api-Contract: shared, dependency-free vocabulary for QED backends, apps and test suites.
// Kotlin Multiplatform so it can be used from the JVM (Ktor backends, test framework, Android)
// and from iOS. iOS targets only build on macOS; on Windows they are skipped.

plugins {
    kotlin("multiplatform") version "2.4.20"
    kotlin("plugin.serialization") version "2.4.20"
    // Publishing — allows consumers (e.g. the test suites) to use this via mavenLocal.
    // Multiplatform creates its publications automatically (one per target plus metadata),
    // so no publishing { } block is needed, and components["java"] doesn't exist here.
    `maven-publish`
}

// group + project name give the coordinates "com.qed:QED-Api-Contract:1.0.0",
// which consumers use; includeBuild substitutes this project for them.
group = "com.qed"
version = "1.0.0"

kotlin {
    jvmToolchain(17)

    // JVM: Ktor backends, QED test framework. Android apps consume this JVM variant as well.
    jvm()

    // iOS device and Apple-silicon simulator
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
        commonMain.dependencies {
            // ErrorResponse is @Serializable. Core only, not json: the contract defines shapes, consumers pick the format
            api("org.jetbrains.kotlinx:kotlinx-serialization-core:1.8.0")
        }
    }
}