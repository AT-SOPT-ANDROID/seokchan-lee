package org.sopt.at.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.sopt.at.convention.extension.debugImplementation
import org.sopt.at.convention.extension.getBundle
import org.sopt.at.convention.extension.getLibrary
import org.sopt.at.convention.extension.implementation
import org.sopt.at.convention.extension.libs

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

    commonExtension.apply {
        buildFeatures {
            compose = true
        }
        extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
            enableStrongSkippingMode.set(true)
            includeSourceInformation.set(true)
        }
        dependencies {
            implementation(platform(libs.getLibrary("compose.bom")))
            implementation(libs.getBundle("compose"))
            debugImplementation(libs.getBundle("compose.debug"))
            implementation(libs.getLibrary("kotlinx.collections"))
        }
    }
}