package org.sopt.at.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.PostSignUpUseCase
import org.sopt.at.model.SignUpInfo
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postSignUpUseCase: PostSignUpUseCase
) :
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
        viewModelScope.launch {
            postSignUpUseCase(
                SignUpInfo(
                    loginId = uiState.value.inputId,
                    password = uiState.value.inputPassword,
                    nickname = uiState.value.inputId
                )
            ).onSuccess {
                postSideEffect(SignUpSideEffect.NavigateToBack)
                Log.d("asdasdasd", "success")
            }.onFailure {
                // TODO. 회원가입 실패
                Log.d("asdasdasd", "fail")
            }
        }
    }
}
