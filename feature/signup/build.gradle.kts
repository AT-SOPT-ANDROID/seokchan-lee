plugins {
    alias(libs.plugins.atsopt.feature)
}

android {
    namespace = "org.sopt.at.signup"
}
dependencies {
    implementation(libs.androidx.runtime.android)
}
