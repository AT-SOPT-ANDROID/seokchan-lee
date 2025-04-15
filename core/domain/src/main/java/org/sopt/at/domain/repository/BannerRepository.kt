package org.sopt.at.domain.repository

import org.sopt.at.model.BannerInfo

interface BannerRepository {
    fun insertBanner(
        category: String,
        title: String,
        image: String,
    ): Result<Boolean>

    fun getBanner(category: String): Result<List<BannerInfo>>
    fun updateFavorite(title: String, isFavorite: Boolean): Result<Boolean>
    fun getFavoriteBanners(): Result<List<BannerInfo>>
    fun deleteAll(): Result<Boolean>
}