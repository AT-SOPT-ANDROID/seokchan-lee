package org.sopt.at.login

import org.sopt.at.ui.base.SideEffect
import org.sopt.at.ui.base.UiState

data class LoginState(
    val id: String = "",
    val password: String = ""
) : UiState

sealed interface LoginSideEffect : SideEffect {
    data object NavigateSignUp : LoginSideEffect
    data object NavigateHome : LoginSideEffect
    data object SignInFailure : LoginSideEffect
}
