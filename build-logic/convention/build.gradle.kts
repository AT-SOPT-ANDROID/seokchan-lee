plugins {
    `kotlin-dsl`
}

group = "org.sopt.at.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.compose.compiler.extension)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "atsopt.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidLibrary") {
            id = "atsopt.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }

        register("androidComposeLibrary") {
            id = "atsopt.android.compose.library"
            implementationClass = "AndroidComposeLibraryPlugin"
        }

        register("androidHilt") {
            id = "atsopt.android.hilt"
            implementationClass = "HiltPlugin"
        }

        register("javaLibrary") {
            id = "atsopt.java.library"
            implementationClass = "JavaLibraryPlugin"
        }

        register("buildConfig") {
            id = "atsopt.plugin.build.config"
            implementationClass = "BuildConfigPlugin"
        }

        register("androidTest") {
            id = "atsopt.plugin.android.test"
            implementationClass = "AndroidTestPlugin"
        }

        register("unitTest") {
            id = "atsopt.plugin.test"
            implementationClass = "UnitTestPlugin"
        }

        register("atsoptFeature") {
            id = "atsopt.feature"
            implementationClass = "AtsoptFeaturePlugin"
        }

        register("atsoptData") {
            id = "atsopt.data"
            implementationClass = "AtsoptDataPlugin"
        }
    }
}