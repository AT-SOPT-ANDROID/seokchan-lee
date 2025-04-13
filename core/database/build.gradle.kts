plugins {
    alias(libs.plugins.atsopt.android.library)
    alias(libs.plugins.atsopt.android.hilt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.at.database"
}

dependencies {
    implementation(projects.core.model)
}
