// QED-Api-Contract: shared, dependency-free vocabulary for QED backends, apps and test suites.
// Kotlin Multiplatform so it can be used from the JVM (Ktor backends, test framework, Android)
// and from iOS. iOS targets only build on macOS; on Windows they are skipped.

plugins {
    kotlin("multiplatform") version "2.4.20"
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
    }
}
