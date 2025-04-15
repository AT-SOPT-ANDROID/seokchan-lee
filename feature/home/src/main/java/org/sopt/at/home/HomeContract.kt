package org.sopt.at.home

import org.sopt.at.model.BannerInfo
import org.sopt.at.home.model.HomeCategory
import org.sopt.at.ui.base.SideEffect
import org.sopt.at.ui.base.UiState

data class HomeState(
    val currentCategory: HomeCategory = HomeCategory.HOME,
    val banners: List<BannerInfo> = emptyList()
) : UiState

sealed interface HomeSideEffect : SideEffect {
    data object NavigateMyPage : HomeSideEffect
}
