package org.sopt.at.buildconfig.impl

import org.sopt.at.buildconfig.BuildConfig
import org.sopt.at.common.buildconfig.BuildConfigFieldProvider
import org.sopt.at.common.buildconfig.BuildConfigFields
import javax.inject.Inject

class BuildConfigFieldsProviderImpl @Inject constructor() : BuildConfigFieldProvider {
    override fun get(): BuildConfigFields =
        BuildConfigFields(
            baseUrl = BuildConfig.BASE_URL,
            isDebug = true
        )
}
