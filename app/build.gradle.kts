plugins {
    alias(libs.plugins.atsopt.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.atsopt.android.hilt)
}

android {
    namespace = "org.sopt.at"
}

dependencies {
    implementation(projects.core.buildconfig)
    implementation(projects.core.network)
    implementation(projects.core.data)
    implementation(projects.feature.main)
}
