plugins {
    alias(libs.plugins.atsopt.android.library)
    alias(libs.plugins.atsopt.android.hilt)
    alias(libs.plugins.atsopt.plugin.test)
    alias(libs.plugins.atsopt.plugin.build.config)
}

android {
    namespace = "org.sopt.at.buildconfig"
}

dependencies {
    implementation(projects.core.common)
}
