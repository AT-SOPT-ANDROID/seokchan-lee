package org.sopt.at.history

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetFavoriteBannerUseCase
import org.sopt.at.domain.usecase.PostFavoriteBannerUseCase
import org.sopt.at.history.model.FavoriteDialogState
import org.sopt.at.history.model.HistoryCategory.Companion.toCategory
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val postFavoriteBannerUseCase: PostFavoriteBannerUseCase,
    private val getFavoriteBannerUseCase: GetFavoriteBannerUseCase,
) :
    BaseViewModel<HistoryState, HistorySideEffect>(HistoryState()) {

    init {
        getFavoriteBanner()
    }

    fun setFavoriteBanner(isFavorite: Boolean) {
        viewModelScope.launch {
            if (uiState.value.inputFavoriteTitle.isNotBlank())
                postFavoriteBannerUseCase(uiState.value.inputFavoriteTitle, isFavorite)
        }
    }

    fun getFavoriteBanner() {
        viewModelScope.launch {
            getFavoriteBannerUseCase().onSuccess {
                intent {
                    copy(
                        favoriteBanners = it
                    )
                }
            }
        }
    }

    fun showFavoriteDialog(shown: FavoriteDialogState) {
        intent {
            copy(
                favoriteDialogShown = shown
            )
        }
    }

    fun updateInputFavoriteTitle(title: String) {
        intent {
            copy(
                inputFavoriteTitle = title
            )
        }
    }

    fun changeCurrentCategory(category: String) {
        intent {
            copy(
                currentCategory = category.toCategory()
            )
        }
    }
}