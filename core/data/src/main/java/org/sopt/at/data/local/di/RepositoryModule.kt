package org.sopt.at.data.local.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.at.data.local.repository.AutoSignInRepositoryImpl
import org.sopt.at.data.local.repository.BannerRepositoryImpl
import org.sopt.at.data.local.repository.UserRepositoryImpl
import org.sopt.at.domain.repository.AutoSignInRepository
import org.sopt.at.domain.repository.BannerRepository
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindBannerRepository(bannerRepositoryImpl: BannerRepositoryImpl): BannerRepository

    @Binds
    @Singleton
    abstract fun bindAutoSignInRepository(
        autoSignInRepository: AutoSignInRepositoryImpl
    ): AutoSignInRepository
}
