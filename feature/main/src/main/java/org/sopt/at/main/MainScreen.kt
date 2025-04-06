package org.sopt.at.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.home.navigation.homeNavGraph
import org.sopt.at.login.navigation.loginNavGraph
import org.sopt.at.signup.navigation.signUpNavGraph

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        modifier = modifier,
        content = { innerPadding ->
            NavHost(
                modifier = modifier
                    .background(color = AtsoptTheme.colors.black)
                    .fillMaxSize(),
                navController = navigator.navController,
                startDestination = navigator.startDestination,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None }
            ) {
                loginNavGraph(
                    padding = innerPadding,
                    navigateToSignUp = navigator::navigateToSignUp,
                    navigateToHome = navigator::navigateToHome
                )
                signUpNavGraph(
                    padding = innerPadding,
                    navigateToSignIn = navigator::navigateToSignIn
                )
                homeNavGraph(
                    padding = innerPadding
                )
            }
        }
    )
}
