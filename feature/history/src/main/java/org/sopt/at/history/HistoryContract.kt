package org.sopt.at.history

import org.sopt.at.history.model.FavoriteDialogState
import org.sopt.at.history.model.HistoryCategory
import org.sopt.at.model.BannerInfo
import org.sopt.at.ui.base.SideEffect
import org.sopt.at.ui.base.UiState

data class HistoryState(
    val currentCategory: HistoryCategory = HistoryCategory.VIEW_HISTORY,
    val favoriteBanners: List<BannerInfo> = emptyList(),
    val favoriteDialogShown: FavoriteDialogState = FavoriteDialogState.UnShown,
    val inputFavoriteTitle: String = ""
) : UiState

sealed interface HistorySideEffect : SideEffect
