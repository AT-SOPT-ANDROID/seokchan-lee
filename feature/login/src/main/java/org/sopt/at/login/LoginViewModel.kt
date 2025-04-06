package org.sopt.at.login

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {
    fun updateUserID(id: String) {
        intent {
            copy(
                id = id
            )
        }
    }

    fun updateUserPassword(password: String) {
        intent {
            copy(
                password = password
            )
        }
    }

    fun navigateToSignUp() {
        postSideEffect(LoginSideEffect.NavigateSignUp)
    }

    fun navigateToHome() {
        postSideEffect(LoginSideEffect.NavigateHome)
    }
}