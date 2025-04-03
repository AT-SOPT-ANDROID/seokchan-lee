import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.sopt.at.convention.extension.getBundle
import org.sopt.at.convention.extension.getLibrary
import org.sopt.at.convention.extension.implementation
import org.sopt.at.convention.extension.libs

class AtsoptFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("atsopt.android.compose.library")
                apply("atsopt.android.hilt")
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            dependencies {
                implementation(libs.getLibrary("kotlinx.serialization.json"))
                implementation(project(":core:ui"))
                implementation(project(":core:designsystem"))
                implementation(project(":core:model"))
                implementation(project(":core:domain"))
                implementation(project(":core:navigation"))
                implementation(libs.getBundle("compose"))
            }
        }
    }
}