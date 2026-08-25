import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.appauthbase"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.example.appauthbase"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        manifestPlaceholders["appAuthRedirectScheme"] = "com.example.appauthbase"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
      compose = true
      aidl = false
      buildConfig = false
      shaders = false
    }

    packaging {
      resources {
        excludes += "/META-INF/{AL2.0,LGPL2.1}"
      }
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  androidTestImplementation(composeBom)

  // Core Android dependencies
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  
  // AppAuth
  implementation(libs.appauth)

  // Arch Components
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.viewmodel.compose)

  // Compose
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)
  // Tooling
  debugImplementation(libs.androidx.compose.ui.tooling)
  // Instrumented tests
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  debugImplementation(libs.androidx.compose.ui.test.manifest)

  // Local tests: jUnit, coroutines, Android runner
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)

  // Instrumented tests: jUnit rules and runners
  androidTestImplementation(libs.androidx.test.core)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.androidx.test.runner)
  androidTestImplementation(libs.androidx.test.espresso.core)

  // Navigation
  implementation(libs.androidx.navigation3.ui)
  implementation(libs.androidx.navigation3.runtime)
  implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.kotlinx.coroutines.android)

    // Ícones do Material (Icons.Filled.*, Icons.AutoMirrored.Filled.*)
    implementation(libs.androidx.compose.material.icons.extended)  // ← adicionar esta linha


}

val localPropertiesFile = rootProject.file("local.properties")
val sdkDirConfig: String? = if (localPropertiesFile.exists()) {
    val properties = Properties()
    localPropertiesFile.inputStream().use { properties.load(it) }
    properties.getProperty("sdk.dir")
} else {
    System.getenv("ANDROID_HOME") ?: System.getenv("ANDROID_SDK_ROOT")
}
val resolvedAdbPath: String = if (!sdkDirConfig.isNullOrBlank()) {
    File(sdkDirConfig, if (System.getProperty("os.name").lowercase().contains("windows")) "platform-tools/adb.exe" else "platform-tools/adb").absolutePath
} else {
    "adb"
}

val setupAdb8080 by tasks.registering(Exec::class) {
    group = "custom"
    description = "Setup adb reverse port 8080"
    commandLine(resolvedAdbPath, "reverse", "tcp:8080", "tcp:8080")
    isIgnoreExitValue = true
}

val setupAdb8081 by tasks.registering(Exec::class) {
    group = "custom"
    description = "Setup adb reverse port 8081"
    commandLine(resolvedAdbPath, "reverse", "tcp:8081", "tcp:8081")
    isIgnoreExitValue = true
}

val setupAdb8082 by tasks.registering(Exec::class) {
    group = "custom"
    description = "Setup adb reverse port 8082"
    commandLine(resolvedAdbPath, "reverse", "tcp:8082", "tcp:8082")
    isIgnoreExitValue = true
}

tasks.named("preBuild") {
    dependsOn(setupAdb8080, setupAdb8081, setupAdb8082)
}

