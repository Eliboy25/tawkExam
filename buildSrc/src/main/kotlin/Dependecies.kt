const val version_kotlin = "1.3.70"

object Plugins {
    private object Versions {
        const val version_gradle = "4.0.0"
        const val version_apollo = "1.3.1"
    }

    const val plugin_gradle_android = "com.android.tools.build:gradle:${Versions.version_gradle}"
    const val plugin_gradle_kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version_kotlin"
    const val plugin_kotlin = "kotlin"
    const val plugin_android_kotlin = "kotlin-android"
    const val plugin_kotlin_kapt = "kotlin-kapt"
    const val plugin_kotlin_android_extensions = "kotlin-android-extensions"
    const val plugin_android_library = "com.android.library"
    const val plugin_library_common = "plugin-library-common"
    const val plugin_apollo =
        "com.apollographql.apollo:apollo-gradle-plugin:${Versions.version_apollo}"
    const val plugin_apollo_graphql = "com.apollographql.apollo"

    const val plugin_gradle_jfrog = "com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4"
    const val plugin_gradle_dcendents = "com.github.dcendents:android-maven-gradle-plugin:2.1"
}

object AndroidSdk {
    const val version_build_tools = "29.0.2"
    const val version_sdk_compile = 29
    const val version_sdk_min = 21
    const val version_sdk_target = version_sdk_compile
}


object Libraries {
    private object Versions {
        const val kotlin_stdlib = version_kotlin
        const val kotlin_coroutines = "1.3.3"

        //AndroidX
        const val androidx_appcompat = "1.1.0"
        const val androidx_core_ktx = "1.3.0"
        const val androidx_constraintlayout = "2.0.0-beta4"
        const val androidx_settings = "1.1.0-rc01"
        const val androidx_coordinatorlayout = "1.1.0"
        const val androidx_recyclerview = "1.1.0"

        //Material components
        const val material_components = "1.1.0-alpha10"
        const val material_date_range_picker = "2.0"

        //KTX
        const val lifecycle_viewmodel = "2.2.0"
        const val lifecycle_scope = "2.2.0"
        const val lifecycle_livedata = "2.2.0"

        //dagger 2
        const val dagger = "2.24"

        //assisted inject
        const val assisted_inject = "0.5.0"

        //conductor
        const val conductor = "3.0.0-rc5"

        //retrofit
        const val retrofit = "2.8.1"
        const val retrofit_adapter = "2.3.0"
        //okhttp
        const val okhttp = "4.5.0"
        //JetBrains annotations
        const val jetbrains_annotations = "13.0"

        //Glide
        const val glide = "4.11.0"

        //Swipe Refresh Layout
        const val swipe_refresh_layout= "1.0.0"

        //Lifecycle Process
        const val lifecycle_process = "2.2.0"

        //Sdp
        const val sdp = "1.0.6"

        //EventBus
        const val eventbus = "3.2.0"

        //Gson
        const val gson = "2.8.6"
        const val gson_converter = "2.7.2"

        //Joda
        const val joda = "2.10.3"

        //Timber
        const val timber = "4.7.1"

        //Room
        const val room = "2.2.5"
    }

    const val dependency_kotlin_stdlib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_stdlib}"
    const val dependency_kotlin_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}"
    const val dependency_kotlin_coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"

    //AndroidX
    const val dependency_androidx_appcompat =
        "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val dependency_androidx_core_ktx =
        "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
    const val dependency_androidx_constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintlayout}"
    const val dependency_androidx_settings =
        "androidx.preference:preference-ktx:${Versions.androidx_settings}"
    const val dependency_androidx_coordinatorlayout =
        "androidx.coordinatorlayout:coordinatorlayout:${Versions.androidx_coordinatorlayout}"
    const val dependency_androidx_recyclerview =
        "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"

    //Material components
    const val dependency_material_components =
        "com.google.android.material:material:${Versions.material_components}"
    const val dependency_material_date_range_picker_range =
        "com.borax12.materialdaterangepicker:library:${Versions.material_date_range_picker}"

    //KTX
    const val dependency_lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_viewmodel}"
    const val dependency_lifecycle_scope =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_scope}"
    const val dependency_lifecycle_livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_livedata}"

    //dagger 2
    const val dependency_dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val kapt_dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    //assisted inject
    const val dependency_assisted_inject =
        "com.squareup.inject:assisted-inject-annotations-dagger2:${Versions.assisted_inject}"
    const val kapt_assisted_inject =
        "com.squareup.inject:assisted-inject-processor-dagger2:${Versions.assisted_inject}"

    //conductor
    const val dependency_conductor = "com.bluelinelabs:conductor:${Versions.conductor}"
    const val dependency_conductor_lifecycle =
        "com.bluelinelabs:conductor-archlifecycle:${Versions.conductor}"

    //retrofit
    const val dependency_gson = "com.google.code.gson:gson:${Versions.gson}"
    const val dependency_gson_converter = "com.squareup.retrofit2:converter-gson:${Versions.gson_converter}"
    const val dependecy_retrofit_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_adapter}"
    const val dependency_retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    //okhttp
    const val dependency_okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val dependency_okhttp_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //JetBrains annotations
    const val dependency_jetbrains_annotations =
        "org.jetbrains:annotations:${Versions.jetbrains_annotations}"

    //Glide
    const val dependency_glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    //SwipeRefreshLayout
    const val dependency_swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipe_refresh_layout}"

    //Sdp
    const val dependecy_sdp = "com.intuit.sdp:sdp-android:${Versions.sdp}"

    //EventBus
    const val dependency_eventbus = "org.greenrobot:eventbus:${Versions.eventbus}"

    //Rxjava
    const val dependency_rxjava2 = "io.reactivex.rxjava2:rxandroid:2.1.1"
    const val dependency_rxkotlin = "io.reactivex:rxkotlin:2.0.0-RC1"
    const val dependency_kotlin_script = "org.jetbrains.kotlin:kotlin-script-runtime:1.3.71"

    //Joda
    const val dependency_joda = "net.danlew:android.joda:${Versions.joda}"

    //Timber
    const val dependency_timber = "com.jakewharton.timber:timber:${Versions.timber}"

    //Room
    const val dependency_room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val dependency_room_compiler = "androidx.room:room-compiler:${Versions.room}"
    const val dependency_room = "androidx.room:room-ktx:${Versions.room}"
   
    const val lifecycle_process = "androidx.lifecycle:lifecycle-process:${Versions.lifecycle_process}"


    //CircleImageview
    const val dependency_circle_imageview = "de.hdodenhof:circleimageview:3.1.0"

    //Spinkit
    const val dependecy_spinkit = "com.github.ybq:Android-SpinKit:1.4.0"

    //Carousel
    const val dependency_carousel = "com.synnapps:carouselview:0.1.5"
    const val dependency_easyimage = "com.github.jkwiecien:EasyImage:3.0.4"
    const val dependecy_compressor = "id.zelory:compressor:2.1.0"

    //Evernote
    const val dependency_evernote = "com.evernote:android-state:1.4.1"
    const val dependecy_evernote_processor = "com.evernote:android-state-processor:1.4.1"

    const val dependency_firebase_crashlytics = "com.google.firebase:firebase-crashlytics:17.0.0"
    const val dependency_firebase_messaging = "com.google.firebase:firebase-messaging:20.1.7"

    const val dependency_materialprogress = "me.zhanghai.android.materialprogressbar:library:1.6.1"
    const val dependency_work = "androidx.work:work-runtime:2.4.0-alpha02"

    const val dependency_picasso = "com.squareup.picasso:picasso:2.71828"
    const val dependecy_signature = "com.github.gcacace:signature-pad:1.3.1"
    const val dependency_vm = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"


    const val dependency_image_inverter = "jp.co.cyberagent.android:gpuimage:2.0.4"

    const val dependency_shimmer= "com.facebook.shimmer:shimmer:0.1.0@aar"


}

object TestLibraries {
    private object Versions {
        const val version_junit = "4.12"
        const val version_androidx_test_runner = "1.2.0"
        const val version_androidx_espresso_core = "3.2.0"
    }

    const val test_instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
    const val test_dependency_junit = "junit:junit:${Versions.version_junit}"
    const val test_dependency_androidx_test_runner =
        "androidx.test:runner:${Versions.version_androidx_test_runner}"
    const val test_dependency_androidx_espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.version_androidx_espresso_core}"
}


