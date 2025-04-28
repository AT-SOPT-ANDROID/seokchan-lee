package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.BannerRepository
import org.sopt.at.model.MockBannerData
import javax.inject.Inject

class PostBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    operator fun invoke(banners: List<MockBannerData>): Result<Boolean> =
        runCatching {
            banners.forEach { banner ->
                bannerRepository.insertBanner(
                    category = banner.category,
                    title = banner.title,
                    image = banner.image
                ).getOrThrow()
            }
            true
        }
}
