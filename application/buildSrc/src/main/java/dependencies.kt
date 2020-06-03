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
    const val retrofit = "2.7.0"
    const val squareMoshi = "1.9.2"
    const val koin = "2.0.1"
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
    val koin = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-android-viewmodel:2.0.1"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val okHttpLogger = "com.squareup.okhttp3:logging-interceptor:3.8.0"
    val squareMoshi = "com.squareup.moshi:moshi:${Versions.squareMoshi}"
    val squareMoshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.squareMoshi}"
    val squareMoshiAdapter = "com.squareup.moshi:moshi-adapters:${Versions.squareMoshi}"
    val glide = "com.github.bumptech.glide:glide:4.9.0"
    val commonsIo = "commons-io:commons-io:2.4"
    val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.0.0"
}

object TestDependencies {
    val androidxRunner = "androidx.test:runner:1.2.0"
    val jUnitExt = "androidx.test.ext:junit:1.1.1"
    val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"
    val espressoIntents = "androidx.test.espresso:espresso-intents:3.2.0"
    val androidxTruth = "androidx.test.ext:truth:1.2.0"

    val jUnit = "junit:junit:4.12"
    val espressoRunner = "androidx.test:runner:1.0.2"
    val espressoContrib = "androidx.test.espresso:espresso-contrib:3.2.0"
    val archCore = "androidx.arch.core:core-testing:2.0.1"
    val testCore = "androidx.test:core:1.2.0"
    val koin = "org.koin:koin-test:${Versions.koin}"
    val mockk = "io.mockk:mockk-android:1.9.3"
    val roboletric = "org.robolectric:robolectric:4.3"
    val kakao = "com.agoda.kakao:kakao:2.1.0"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:3.8.0"
    val httpIdlingResourcce = "com.jakewharton.espresso:okhttp3-idling-resource:1.0.0"
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
        Dependencies.koinViewModel,
        Dependencies.glide,
        Dependencies.swipeRefreshLayout
    )

    val data = listOf(
        Dependencies.koin,
        Dependencies.coreKtx,
        Dependencies.kotlinStdlib,
        Dependencies.retrofit,
        Dependencies.retrofitConverter,
        Dependencies.retrofitMoshi,
        Dependencies.squareMoshi,
        Dependencies.squareMoshiKotlin,
        Dependencies.squareMoshiAdapter,
        Dependencies.commonsIo,
        Dependencies.okHttpLogger
    )

    val domain = listOf(
        Dependencies.koin,
        Dependencies.kotlinStdlib
    )

    val unitTesting = listOf(
        TestDependencies.jUnit
    )

    val androidTesting = listOf(
        TestDependencies.androidxRunner,
        TestDependencies.jUnitExt,
        TestDependencies.espressoCore,
        TestDependencies.espressoIntents,
        TestDependencies.androidxTruth,
        TestDependencies.koin,
        TestDependencies.mockk,
        TestDependencies.kakao,
        TestDependencies.mockWebServer,
        TestDependencies.httpIdlingResourcce,
        Dependencies.commonsIo
    )
}