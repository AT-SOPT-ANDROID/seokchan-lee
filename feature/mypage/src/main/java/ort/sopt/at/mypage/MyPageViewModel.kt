package ort.sopt.at.mypage

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetMyNicknameUseCase
import org.sopt.at.domain.usecase.SetAutoSignInUseCase
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val setAutoSignInUseCase: SetAutoSignInUseCase,
    private val getMyNicknameUseCase: GetMyNicknameUseCase
) : BaseViewModel<MyPageState, MyPageSideEffect>(MyPageState()) {

    fun cancelAutoSignIn() {
        viewModelScope.launch {
            setAutoSignInUseCase(false)
        }
    }

    fun getMyNickname() {
        viewModelScope.launch {
            getMyNicknameUseCase()
                .onSuccess {
                    intent {
                        copy(
                            nickname = it
                        )
                    }
                }
        }
    }

    fun navigateToSignIn() {
        postSideEffect(MyPageSideEffect.NavigateSignIn)
    }
}
