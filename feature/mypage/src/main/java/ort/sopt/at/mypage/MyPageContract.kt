package ort.sopt.at.mypage

import org.sopt.at.ui.base.SideEffect
import org.sopt.at.ui.base.UiState

data class MyPageState(
    val nickname: String = ""
) : UiState

sealed interface MyPageSideEffect : SideEffect {
    data object NavigateSignIn : MyPageSideEffect
}
