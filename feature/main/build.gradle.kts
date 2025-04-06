plugins {
    alias(libs.plugins.atsopt.feature)
}

android {
    namespace = "org.sopt.at.main"
}

dependencies {
    implementation(projects.feature.login)
    implementation(projects.feature.signup)
    implementation(projects.feature.home)
}