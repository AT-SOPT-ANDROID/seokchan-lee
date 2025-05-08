package ort.sopt.at.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.extension.clickableWithoutRipple
import org.sopt.at.ui.lifecycle.LaunchedEffectWithLifecycle

@Composable
fun MyPageRoute(
    navigateToSignIn: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                MyPageSideEffect.NavigateSignIn -> navigateToSignIn()
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.apply {
            getMyNickname()
        }
    }

    MyPageScreen(
        nickname = uiState.nickname,
        logout = viewModel::cancelAutoSignIn,
        navigateToSignUp = viewModel::navigateToSignIn
    )
}

@Composable
fun MyPageScreen(
    nickname: String,
    logout: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = nickname,
            color = AtsoptTheme.colors.white,
            style = AtsoptTheme.typography.bodyMedium13,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        Text(
            text = "logout",
            color = AtsoptTheme.colors.white,
            modifier = Modifier.clickableWithoutRipple {
                logout().also {
                    navigateToSignUp()
                }
            }
        )
    }
}
