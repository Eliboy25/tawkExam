plugins {
    `kotlin-dsl`
}

buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://maven.google.com")

    }
    dependencies {
        classpath(Plugins.plugin_gradle_android)
        classpath(Plugins.plugin_gradle_kotlin)
        classpath(Plugins.plugin_apollo)
        classpath(Plugins.plugin_gradle_jfrog)
        classpath(Plugins.plugin_gradle_dcendents)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
        maven(url = "https://oss.jfrog.org/libs-snapshot")
        maven(url = "https://oss.jfrog.org/libs-snapshot/io/reactivex/rxjava3/rxandroid/")
    }
}

dependencies {
    compileOnly(Libraries.dependency_jetbrains_annotations)
    testCompileOnly(Libraries.dependency_jetbrains_annotations)
}