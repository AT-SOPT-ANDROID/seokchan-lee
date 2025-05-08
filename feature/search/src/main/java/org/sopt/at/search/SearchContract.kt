package org.sopt.at.search

import okhttp3.internal.immutableListOf
import org.sopt.at.ui.base.SideEffect
import org.sopt.at.ui.base.UiState

data class SearchState(
    val keyword: String = "",
    val userNickname: List<String> = immutableListOf()
) : UiState

sealed interface SearchSideEffect : SideEffect
