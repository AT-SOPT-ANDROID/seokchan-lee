import com.android.build.api.dsl.LibraryExtension
import org.sopt.at.convention.extension.androidTestImplementation
import org.sopt.at.convention.extension.debugImplementation
import org.sopt.at.convention.extension.getLibrary
import org.sopt.at.convention.extension.implementation
import org.sopt.at.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                androidTestImplementation(libs.getLibrary("androidx.test.runner"))
                debugImplementation(libs.getLibrary("androidx.test.core"))
                androidTestImplementation(libs.getLibrary("kotlinx.coroutines.test"))
                implementation(libs.getLibrary("androidx.test.ext.junit"))
            }

            extensions.configure<LibraryExtension> {
                defaultConfig {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
            }
        }
    }
}