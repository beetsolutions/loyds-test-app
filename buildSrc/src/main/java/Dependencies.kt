object Dependencies {

    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:2.37"

    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val androidCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2"
    private const val jsonSerializer = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0"
    private const val annotation = "androidx.annotation:annotation:1.2.0"
    private const val runtime = "androidx.lifecycle:lifecycle-runtime:2.4.0"
    private const val runtimeKt = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
    private const val extensions = "androidx.lifecycle:lifecycle-extensions:2.1.0"
    private const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:2.1.0"
    private const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.5"
    private const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:2.3.1"
    private const val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:2.3.1"
    private const val avoidConflict = "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"
    private const val animatedHost = "com.google.accompanist:accompanist-navigation-animation:0.24.5-alpha"
    private const val timber = "com.jakewharton.timber:timber:4.7.1"
    private const val retrofit2 = "com.squareup.retrofit2:retrofit:2.9.0"
    private const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    private const val okhttp3 = "com.squareup.okhttp3:okhttp:4.9.0"
    private const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    private const val leakcanary = "com.squareup.leakcanary:leakcanary-android:2.7"
    private const val composeRuntime = "androidx.compose.runtime:runtime:1.2.0-alpha06"
    private const val composeUI = "androidx.compose.ui:ui:1.2.0-alpha06"
    private const val composeMaterial = "androidx.compose.material:material:1.2.0-alpha06"
    private const val composeUIToolingPreview = "androidx.compose.ui:ui-tooling-preview:1.2.0-alpha06"
    private const val composeUITooling = "androidx.compose.ui:ui-tooling:1.2.0-alpha06"
    private const val composeActivity = "androidx.activity:activity-compose:1.4.0"
    private const val composeNavigation = "androidx.navigation:navigation-compose:2.5.0-alpha02"
    private const val composeFlowLayout = "com.google.accompanist:accompanist-flowlayout:0.20.0"
    private const val composeIconExtended = "androidx.compose.material:material-icons-extended:1.0.5"
    private const val accompanistInsets = "com.google.accompanist:accompanist-insets:0.22.1-rc"
    private const val composeHiltNavigation = "androidx.hilt:hilt-navigation-compose:1.0.0-rc01"

    const val hiltCompiler = "com.google.dagger:hilt-compiler:2.37"
    private const val hiltAndroid = "com.google.dagger:hilt-android:2.37"
    private const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"

    private const val junit = "junit:junit:${Versions.junit}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    private const val uiTest = "androidx.compose.ui:ui-test:1.2.0-alpha06"
    private const val uiTestJUnit4 = "androidx.compose.ui:ui-test-junit4:1.2.0-alpha06"

    const val hiltTesting = "com.google.dagger:hilt-android-testing:2.37"
    const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(material)
        add(androidCoroutines)
        add(jsonSerializer)
        add(annotation)
        add(runtime)
        add(extensions)
        add(lifecycleCompiler)
        add(fragmentKtx)
        add(navigationFragmentKtx)
        add(navigationUIKtx)
        add(avoidConflict)
        add(okhttp3)
        add(okhttp3Logging)
        add(timber)
        add(retrofit2)
        add(retrofit2ConverterGson)
        add(composeRuntime)
        add(composeUI)
        add(composeMaterial)
        add(composeUITooling)
        add(runtimeKt)
        add(composeActivity)
        add(composeNavigation)
        add(composeFlowLayout)
        add(composeIconExtended)
        add(composeHiltNavigation)
        add(accompanistInsets)
        add(hiltAndroid)
        add(hiltLifecycleViewModel)
        add(animatedHost)
    }
    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(uiTest)
        add(uiTestJUnit4)
        add(hiltTesting)
        add(hiltAndroid)
    }
    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }
    val kaptLibraries = arrayListOf<String>().apply {
        add(lifecycleCompiler)
        add(hiltCompiler)
    }
    val debugLibraries = arrayListOf<String>().apply {
        add(leakcanary)
        add(composeUIToolingPreview)
    }
}
