package org.sopt.at.database.source

import org.sopt.at.model.BannerInfo

interface HomeBannerDataSource {
    fun insertBanner(
        category: String,
        title: String,
        image: String,
    ): Boolean

    fun getBanner(category: String): List<BannerInfo>
    fun updateFavorite(title: String, isFavorite: Boolean): Boolean
    fun getFavoriteBanners(): List<BannerInfo>
    fun deleteAll(): Boolean
}