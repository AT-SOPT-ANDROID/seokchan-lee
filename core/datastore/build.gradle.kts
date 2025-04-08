plugins {
    alias(libs.plugins.atsopt.android.library)
    alias(libs.plugins.atsopt.android.hilt)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "org.sopt.at.datastore"
}

dependencies {
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.datastore)
}