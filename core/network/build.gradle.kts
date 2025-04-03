plugins {
    alias(libs.plugins.atsopt.android.library)
    alias(libs.plugins.atsopt.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.atsopt.plugin.test)
}

android {
    namespace = "org.sopt.at.network"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.process.phoenix)
}
