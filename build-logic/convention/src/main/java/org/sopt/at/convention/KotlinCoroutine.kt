package org.sopt.at.convention

import com.android.build.api.dsl.CommonExtension
import org.sopt.at.convention.extension.getBundle
import org.sopt.at.convention.extension.implementation
import org.sopt.at.convention.extension.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinCoroutine(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        dependencies {
            implementation(libs.getBundle("coroutine"))
        }
    }
}