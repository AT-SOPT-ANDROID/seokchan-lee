package org.sopt.at.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.SetAutoSignInUseCase
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setAutoSignInUseCase: SetAutoSignInUseCase,
) : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {
    init {
        setAutoSignIn(true)
    }

    fun setAutoSignIn(autoSignIn: Boolean) {
        viewModelScope.launch {
            setAutoSignInUseCase.invoke(autoSignIn)
        }
    }

    fun navigateToSignUp() {
        postSideEffect(HomeSideEffect.NavigateSignUp)
    }
}