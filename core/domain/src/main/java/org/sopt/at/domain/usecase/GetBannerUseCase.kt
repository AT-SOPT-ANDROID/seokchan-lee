package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.BannerRepository
import org.sopt.at.model.BannerInfo
import javax.inject.Inject

class GetBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
) {
    operator fun invoke(category: String): Result<List<BannerInfo>> =
        bannerRepository.getBanner(category = category)
}
