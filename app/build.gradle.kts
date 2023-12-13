plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.startlearning.khabar24x7"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.startlearning.khabar24x7"
        minSdk = 21
        targetSdk = 33
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // version variables
    val nav_version = "2.7.5"
    val paging_version = "3.2.1"
    val lifecycle_version = "2.6.2"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3-android:1.2.0-alpha03")
    // navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")
    // retrofit and gson
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")
    //daggar
    implementation("com.google.dagger:dagger: 2.48")
    implementation("com.google.dagger:hilt-android:2.46")
    kapt("com.google.dagger:hilt-compiler:2.46")
    // Room components
    implementation("androidx.room:room-runtime:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test:runner:1.5.2")
    kapt("androidx.room:room-compiler:2.6.0")
    //DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    //RX java
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.5")
    // paginations
    implementation("androidx.paging:paging-runtime:$paging_version")
    testImplementation("androidx.paging:paging-common:$paging_version")
    implementation("androidx.paging:paging-rxjava2:$paging_version")
    implementation("androidx.paging:paging-rxjava3:$paging_version")
    implementation("androidx.paging:paging-guava:$paging_version")
    implementation("androidx.paging:paging-compose:3.3.0-alpha02")
    implementation("androidx.paging:paging-compose:1.0.0-alpha13")

    //material icon
    implementation("androidx.compose.material:material-icons-extended:1.6.0-beta02")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")
    implementation("com.github.bumptech.glide:okhttp3-integration:4.12.0")
    implementation("com.github.bumptech.glide:recyclerview-integration:4.12.0")
    implementation("com.github.bumptech.glide:compose:1.0.0-beta01")
//viewModel and Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-service:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-process:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.4")

    implementation("io.coil-kt:coil-compose:1.4.0")
    //truth mockito and turbine
    androidTestImplementation("com.google.truth:truth:1.1.4")
    testImplementation("com.google.truth:truth:1.1.4")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-inline:3.8.0")
    androidTestImplementation("org.mockito:mockito-android:3.12.4")
    implementation("app.cash.turbine:turbine:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}