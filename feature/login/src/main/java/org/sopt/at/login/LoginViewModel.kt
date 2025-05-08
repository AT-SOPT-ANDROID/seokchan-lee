package org.sopt.at.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetAutoSignInUseCase
import org.sopt.at.domain.usecase.PostSignInUseCase
import org.sopt.at.domain.usecase.SetUserTokenUseCase
import org.sopt.at.model.SignInInfo
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postSignInUseCase: PostSignInUseCase,
    private val getAutoSignInUseCase: GetAutoSignInUseCase,
    private val setUserTokenUseCase: SetUserTokenUseCase,
) :
    BaseViewModel<LoginState, LoginSideEffect>(LoginState()) {
    init {
        checkAutoSignIn()
    }

    private fun checkAutoSignIn() {
        viewModelScope.launch {
            getAutoSignInUseCase().onSuccess {
                it.collect { autoSignIn ->
                    if (autoSignIn) postSideEffect(LoginSideEffect.NavigateHome)
                }
            }
        }
    }

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
            postSignInUseCase(SignInInfo(uiState.value.id, uiState.value.password))
                .onSuccess {
                    setUserTokenUseCase(it).also {
                        postSideEffect(LoginSideEffect.NavigateHome)
                    }
                }.onFailure {
                    postSideEffect(LoginSideEffect.SignInFailure)
                }
        }
    }
}
