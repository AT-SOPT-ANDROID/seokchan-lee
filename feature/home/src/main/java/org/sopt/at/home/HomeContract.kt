package org.sopt.at.home

import org.sopt.at.ui.base.SideEffect
import org.sopt.at.ui.base.UiState

data class HomeState(
    val d: String = ""
) : UiState

sealed interface HomeSideEffect : SideEffect {
    data object NavigateSignUp : HomeSideEffect
}