import com.android.build.api.dsl.Packaging

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.ideacode.apmtools.lib"
    compileSdk = 34

    buildFeatures {
        prefab = true
        buildConfig = true
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "VERSION", "\"1.0.0\"")

        ndk {
            abiFilters += setOf("armeabi-v7a","arm64-v8a","x86_64")
        }

        externalNativeBuild {
            cmake {
                cppFlags += "-std=c++17"      // 使用 C++17 标准
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }
    }

    packagingOptions {
        jniLibs {
            excludes += "*/libbytehook.so"
        }
    }

    externalNativeBuild {
        cmake {
            path = file("src/main/cpp/CMakeLists.txt") // 指定 CMakeLists.txt 路径
            version = "3.22.1" // 可选：指定 CMake 版本（建议与本地一致）
        }
    }

    ndkVersion = "29.0.13113456"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // 第三方库
    debugImplementation(libs.leakCanary)
    implementation(libs.shadowhook)
    implementation(libs.bytehook)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}