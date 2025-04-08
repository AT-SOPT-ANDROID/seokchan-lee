package org.sopt.at.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.extension.clickableWithoutRipple
import org.sopt.at.ui.lifecycle.LaunchedEffectWithLifecycle

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                HomeSideEffect.NavigateSignUp -> navigateToSignIn()
            }
        }
    }

    HomeScreen(
        logout = viewModel::setAutoSignIn,
        navigateToSignUp = viewModel::navigateToSignUp
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    logout: (Boolean) -> Unit,
    navigateToSignUp: () -> Unit,
) {
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "home",
            color = AtsoptTheme.colors.white
        )
        Text(
            modifier = Modifier.clickableWithoutRipple {
                logout(false).also {
                    navigateToSignUp()
                }
            },
            text = "logout",
            color = AtsoptTheme.colors.white
        )
    }
}
