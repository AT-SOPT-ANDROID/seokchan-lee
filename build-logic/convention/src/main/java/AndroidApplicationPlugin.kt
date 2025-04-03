import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.sopt.at.convention.configureAndroidCompose
import org.sopt.at.convention.configureKotlinAndroid
import org.sopt.at.convention.extension.getLibrary
import org.sopt.at.convention.extension.getVersion
import org.sopt.at.convention.extension.implementation
import org.sopt.at.convention.extension.libs

internal class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                with(defaultConfig) {
                    targetSdk = libs.getVersion("targetSdk").requiredVersion.toInt()
                    versionCode = libs.getVersion("versionCode").requiredVersion.toInt()
                    versionName = libs.getVersion("versionName").requiredVersion
                }
            }

            dependencies {
                implementation(libs.getLibrary("timber"))
            }
        }
    }
}