package org.sopt.at.buildconfig.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.at.buildconfig.impl.BuildConfigFieldsProviderImpl
import org.sopt.at.common.buildconfig.BuildConfigFieldProvider
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BuildConfigModule {
    @Provides
    @Singleton
    fun provideBuildConfigFieldsProvider(
        buildConfigFieldProvider: BuildConfigFieldsProviderImpl
    ): BuildConfigFieldProvider = buildConfigFieldProvider
}
