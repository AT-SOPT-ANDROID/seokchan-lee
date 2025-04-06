package org.sopt.at.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetSignInUseCase
import org.sopt.at.model.UserInfo
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getSignInUseCase: GetSignInUseCase,
) :
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
        viewModelScope.launch {
            getSignInUseCase.invoke(UserInfo(uiState.value.id, uiState.value.password))
                .onSuccess { signInSuccess ->
                    if (signInSuccess) {
                        postSideEffect(LoginSideEffect.NavigateHome)
                    } else {
                        //TODO. 로그인 실패
                    }
                }
        }
    }
}