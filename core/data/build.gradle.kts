plugins {
    alias(libs.plugins.atsopt.data)
}

android {
    namespace = "org.sopt.at.data"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.datastore)
    implementation(projects.core.database)
}
