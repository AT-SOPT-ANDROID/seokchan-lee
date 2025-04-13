package ort.sopt.at.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.extension.clickableWithoutRipple
import org.sopt.at.ui.lifecycle.LaunchedEffectWithLifecycle

@Composable
fun MyPageRoute(
    navigateToSignIn: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                MyPageSideEffect.NavigateSignIn -> navigateToSignIn()
            }
        }
    }

    MyPageScreen(
        logout = viewModel::cancelAutoSignIn,
        navigateToSignUp = viewModel::navigateToSignIn,
    )
}

@Composable
fun MyPageScreen(
    logout: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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