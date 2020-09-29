plugins {
    id("com.android.application")
    id(Plugins.plugin_android_kotlin)
    id(Plugins.plugin_kotlin_android_extensions)
    id(Plugins.plugin_kotlin_kapt)
}

android {
//    signingConfigs {
//        create("config") {
//            storeFile =
//                file("/Users/jaccabuhat/Documents/AndroidStudioProjects/SunMoonTechProjects/FoodClub/food_club_signed.jks")
//            storePassword = "android"
//            keyAlias = "foodclubalias"
//            keyPassword = "android"
//        }
//    }
    buildToolsVersion(AndroidSdk.version_build_tools)
    compileSdkVersion(AndroidSdk.version_sdk_compile)
    defaultConfig {
        versionCode = VERSION_CODE
        versionName = VERSION_NAME
        minSdkVersion(AndroidSdk.version_sdk_min)
        targetSdkVersion(AndroidSdk.version_sdk_target)
        testInstrumentationRunner = TestLibraries.test_instrumentation_runner

        buildConfigField("boolean", "RELEASE", "false")
        buildConfigField("boolean", "QA", "false")
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-SNAPSHOT"
            buildConfigField("String", "SERVER_URL", "\"https://api.github.com/\"")
        }

        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            buildConfigField("boolean", "RELEASE", "true")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("qa") {
            isShrinkResources = false
            isMinifyEnabled = false
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-ALPHA"
            buildConfigField("String", "SERVER_URL", "\"https://api.github.com/\"")
            buildConfigField("boolean", "QA", "true")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    flavorDimensions("skins")
    productFlavors {
        for (project in projects) {
            create(project.getFlavorName()) {
                applicationId = project.appId
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    applicationVariants.all {
        projects.find {
            flavorName.contains(it.getFlavorName(), true)
        }.let { project ->
            val buildTypeName = buildType.name
            val projectName = project?.name ?: "Application"
            val appName = if (buildTypeName.contains("release", true))
                projectName else "$projectName $buildTypeName"
            mergedFlavor.manifestPlaceholders["applicationName"] = appName
        }

        //This is to provide fresh chat sdk with the file provider
        resValue("string", "freshchat_file_provider_authority", "\"$applicationId.provider\"")
    }
}

dependencies {
    implementation(Libraries.lifecycle_process)
    implementation(Libraries.dependency_androidx_core_ktx)

    api(Libraries.dependency_androidx_appcompat)
    api(Libraries.dependency_material_components)

    api(Libraries.dependency_kotlin_stdlib)
    api(Libraries.dependency_kotlin_coroutines)
    api(Libraries.dependency_kotlin_coroutines_android)
    api(Libraries.dependency_dagger)
    api(Libraries.dependency_assisted_inject)
    androidTestImplementation("androidx.test:rules:1.3.0")

    kapt(Libraries.kapt_dagger_compiler)
    kapt(Libraries.kapt_assisted_inject)

    api(Libraries.dependency_androidx_coordinatorlayout)
    api(Libraries.dependency_androidx_recyclerview)
    api(Libraries.dependency_androidx_constraintlayout)
    api(Libraries.dependency_androidx_appcompat)
    api(Libraries.dependency_material_components)

    implementation(Libraries.dependecy_sdp)
    api(Libraries.dependency_swipe_refresh_layout)

    implementation(Libraries.dependency_eventbus)
    implementation(Libraries.dependency_joda)
    implementation(Libraries.dependency_timber)

    /*Conductor*/
    implementation(Libraries.dependency_conductor)
    implementation(Libraries.dependency_conductor_lifecycle)

    /*RxJava*/
    implementation(Libraries.dependency_rxjava2)
    implementation(Libraries.dependency_rxkotlin)
    implementation(Libraries.dependency_kotlin_script)

    /*Network*/
    implementation(Libraries.dependency_gson)
    implementation(Libraries.dependency_gson_converter)
    implementation(Libraries.dependency_retrofit)
    implementation(Libraries.dependecy_retrofit_adapter)
    implementation(Libraries.dependency_okhttp)
    implementation(Libraries.dependency_okhttp_logging_interceptor)

    implementation(Libraries.dependency_room_runtime)
    kapt(Libraries.dependency_room_compiler)
    implementation(Libraries.dependency_room)

    implementation(Libraries.dependency_glide)


    implementation(Libraries.dependency_easyimage)
    implementation(Libraries.dependecy_compressor)

    implementation(Libraries.dependency_evernote)
    kapt(Libraries.dependecy_evernote_processor)

    implementation(Libraries.dependency_firebase_crashlytics)
    implementation(Libraries.dependency_firebase_messaging)

    implementation(Libraries.dependency_materialprogress)
    implementation(Libraries.dependency_work)
    implementation(Libraries.dependency_picasso)
    implementation(Libraries.dependecy_signature)

    implementation(Libraries.dependency_lifecycle_viewmodel)
    implementation(Libraries.dependency_lifecycle_scope)
    implementation(Libraries.dependency_lifecycle_livedata)


    //CircleImageview
    implementation(Libraries.dependency_circle_imageview)
    implementation(Libraries.dependecy_spinkit)


    implementation(Libraries.dependency_shimmer)


    implementation(TestLibraries.test_dependency_junit)
    implementation(TestLibraries.test_dependency_androidx_test_runner)
    implementation(TestLibraries.test_dependency_androidx_espresso_core)


}
