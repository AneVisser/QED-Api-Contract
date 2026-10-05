# QED-Api-Contract

Shared, dependency-free vocabulary for QED backends, apps and test suites, written in
Kotlin Multiplatform (JVM and iOS; Android uses the JVM variant).

It contains only generic building blocks:

- `RequestType` and `Environment`
- `PermissionKey`, `RoleKey` and `PermissionRequirement` (`qed.contract.auth`)
- `RouteGroupDefinition` and `RouteDefinition` (`qed.contract.routes`)

Applications implement these with their own enums (permissions, roles, route groups, routes).
Application-specific definitions do **not** belong in this module.

## Usage

Consumers depend on `com.qed:QED-Api-Contract:<version>`. For local development, include it as a
composite build, e.g. in `settings.gradle.kts`:

```kotlin
includeBuild("../QED-Api-Contract")
```

## Build

```
gradlew build
```

iOS targets are only built on macOS.
