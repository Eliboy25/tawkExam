import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

fun LibraryExtension.configure() {
    setCompileSdkVersion(AndroidSdk.version_sdk_compile)
    buildToolsVersion = AndroidSdk.version_build_tools
    defaultConfig {
        minSdkVersion(AndroidSdk.version_sdk_min)
        targetSdkVersion(AndroidSdk.version_sdk_target)
        testInstrumentationRunner = TestLibraries.test_instrumentation_runner
    }

    buildTypes {
        create("qa")
    }

    flavorDimensions("skins")
    productFlavors {
        for (project in projects) {
            create(project.getFlavorName())
        }
    }
}

class PluginLibraryCommon : Plugin<Project> {
    override fun apply(target: Project) {
        val extension = target.extensions.getByType<LibraryExtension>()
        extension.configure()
    }
}