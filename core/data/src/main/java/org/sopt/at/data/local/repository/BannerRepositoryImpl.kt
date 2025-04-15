package org.sopt.at.data.local.repository

import org.sopt.at.database.source.HomeBannerDataSource
import org.sopt.at.domain.repository.BannerRepository
import org.sopt.at.model.BannerInfo
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(
    private val homeBannerDataSource: HomeBannerDataSource
) : BannerRepository {
    override fun insertBanner(category: String, title: String, image: String): Result<Boolean> =
        runCatching {
            homeBannerDataSource.insertBanner(
                category = category,
                title = title,
                image = image
            )
        }

    override fun getBanner(category: String): Result<List<BannerInfo>> =
        runCatching { homeBannerDataSource.getBanner(category) }

    override fun updateFavorite(title: String, isFavorite: Boolean): Result<Boolean> =
        runCatching { homeBannerDataSource.updateFavorite(title = title, isFavorite = isFavorite) }

    override fun getFavoriteBanners(): Result<List<BannerInfo>> =
        runCatching { homeBannerDataSource.getFavoriteBanners() }

    override fun deleteAll(): Result<Boolean> =
        runCatching { homeBannerDataSource.deleteAll() }
}
