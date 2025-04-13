plugins {
    alias(libs.plugins.atsopt.java.library)
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.hilt.core)
}
