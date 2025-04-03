plugins {
    alias(libs.plugins.atsopt.feature)
}

android {
    namespace = "org.sopt.at.main"
}

dependencies {
    implementation(projects.feature.home)
}
