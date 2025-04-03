plugins {
    alias(libs.plugins.atsopt.android.library)
    alias(libs.plugins.atsopt.android.hilt)
    alias(libs.plugins.atsopt.plugin.test)
}

android {
    namespace = "org.sopt.at.common"
}
