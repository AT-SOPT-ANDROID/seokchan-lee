package org.sopt.at.signup

import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() :
    BaseViewModel<SignUpState, SignUpSideEffect>(SignUpState()) {

    fun updateInputId(id: String) {
        intent {
            copy(
                inputId = id
            )
        }
    }

    fun updateInputPassword(password: String) {
        intent {
            copy(
                inputPassword = password
            )
        }
    }

    fun navigateToBack() {
        postSideEffect(SignUpSideEffect.NavigateToBack)
    }
}