plugins {
    alias(libs.plugins.atsopt.android.compose.library)
}

android {
    namespace = "org.sopt.at.designsystem"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.model)
}
