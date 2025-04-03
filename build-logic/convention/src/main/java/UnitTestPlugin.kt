import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.sopt.at.convention.extension.getLibrary
import org.sopt.at.convention.extension.libs
import org.sopt.at.convention.extension.testImplementation

class UnitTestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                testImplementation(libs.getLibrary("kotlinx.coroutines.test"))
                testImplementation(libs.getLibrary("mockito"))
                testImplementation(libs.getLibrary("junit"))
                testImplementation(libs.getLibrary("robolectric"))
            }

            extensions.configure<LibraryExtension> {
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                    }
                }
            }
        }
    }
}