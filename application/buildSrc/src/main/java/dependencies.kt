// Versions for project parameters and dependencies

internal object Kotlin {
    const val version = "1.3.72"
}

internal object Versions {
    const val androidGradlePlugin = "3.6.3"
    const val kotlinxSerialization = "0.6.2"
    const val testLogger = "1.4.0"
    const val navigation = "2.2.2"
    const val materialDesign = "1.1.0"
}

object BuildPlugins {
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    val kotlinxSerializiationPlugin =
        "org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:${Versions.kotlinxSerialization}"
    val testLoggerPlugin = "com.adarshr:gradle-test-logger-plugin:${Versions.testLogger}"
}

object Dependencies {
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Kotlin.version}"
    val appCompat = "androidx.appcompat:appcompat:1.1.0"
    val coreKtx = "androidx.core:core-ktx:1.2.0"
    val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    val koin = "org.koin:koin-android:2.0.1"
    val koinViewModel = "org.koin:koin-android-viewmodel:2.0.1"
}

object TestDependencies {
    val jUnit = "junit:junit:4.12"
    val jUnitExt = "androidx.test.ext:junit:1.1.1"
    val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
}

object AndroidModule {
    val main = listOf(
        Dependencies.kotlinStdlib,
        Dependencies.appCompat,
        Dependencies.coreKtx,
        Dependencies.materialDesign,
        Dependencies.constraintLayout,
        Dependencies.navigationFragment,
        Dependencies.navigationUi,
        Dependencies.koin,
        Dependencies.koinViewModel
    )

    val unitTesting = listOf(
        TestDependencies.jUnit
    )

    val androidTesting = listOf(
        TestDependencies.jUnitExt,
        TestDependencies.espressoCore
    )
}