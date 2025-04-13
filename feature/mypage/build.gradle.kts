plugins {
    alias(libs.plugins.atsopt.feature)
}

android {
    namespace = "org.sopt.at.mypage"
}
dependencies {
    implementation(libs.androidx.runtime.android)
}
