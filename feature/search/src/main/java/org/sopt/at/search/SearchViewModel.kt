package org.sopt.at.search

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetUserNicknameUseCase
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getUserNicknameUseCase: GetUserNicknameUseCase
) : BaseViewModel<SearchState, SearchSideEffect>(SearchState()) {

    init {
        viewModelScope.launch {
            uiState
                .map { it.keyword }
                .debounce(300)
                .distinctUntilChanged()
                .collect { keyword ->
                    if (keyword.isNotEmpty()) {
                        searchUserNickname(keyword)
                    }
                }
        }
    }

    fun updateSearchKeyword(keyword: String) {
        intent {
            copy(
                keyword = keyword
            )
        }
    }

    private fun searchUserNickname(keyword: String) {
        viewModelScope.launch {
            getUserNicknameUseCase.invoke(keyword).onSuccess {
                intent {
                    copy(
                        userNickname = it
                    )
                }
            }
        }
    }
}
