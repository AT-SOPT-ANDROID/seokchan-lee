package org.sopt.at.signup

import org.sopt.at.ui.base.SideEffect
import org.sopt.at.ui.base.UiState

data class SignUpState(
    val inputId: String = "",
    val inputPassword: String = "",
) : UiState

sealed interface SignUpSideEffect : SideEffect {
    data object NavigateToBack : SignUpSideEffect
}