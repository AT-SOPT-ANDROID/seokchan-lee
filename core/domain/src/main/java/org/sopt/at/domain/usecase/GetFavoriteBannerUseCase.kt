package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.BannerRepository
import org.sopt.at.model.BannerInfo
import javax.inject.Inject

class GetFavoriteBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    operator fun invoke(): Result<List<BannerInfo>> =
        bannerRepository.getFavoriteBanners()
}
