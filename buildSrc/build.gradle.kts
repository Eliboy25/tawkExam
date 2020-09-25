plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

repositories {
    jcenter()
    google()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    implementation("com.android.tools.build:gradle:4.0.0")
}

gradlePlugin {
    plugins {
        register("PluginLibraryCommon") {
            id = "plugin-library-common"
            implementationClass = "PluginLibraryCommon"
        }
    }
}