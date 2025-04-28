package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.BannerRepository
import javax.inject.Inject

class PostFavoriteBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    operator fun invoke(title: String, isFavorite: Boolean): Result<Boolean> =
        bannerRepository.updateFavorite(title = title, isFavorite = isFavorite)
}
