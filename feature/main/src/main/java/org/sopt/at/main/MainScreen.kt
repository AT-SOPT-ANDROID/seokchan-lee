package org.sopt.at.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import kotlinx.collections.immutable.toPersistentList
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.history.navigation.historyNavGraph
import org.sopt.at.home.navigation.homeNavGraph
import org.sopt.at.live.navigation.liveNavGraph
import org.sopt.at.login.navigation.loginNavGraph
import org.sopt.at.main.component.MainBottomBar
import org.sopt.at.search.navigation.searchNavGraph
import org.sopt.at.shorts.navigation.shortsNavGraph
import org.sopt.at.signup.navigation.signUpNavGraph
import ort.sopt.at.mypage.navigation.myPageNavGraph

@Composable
internal fun MainScreen(
    modifier: Modifier = Modifier,
    navigator: MainNavigator = rememberMainNavigator()
) {
    Scaffold(
        modifier = modifier,
        content = { innerPadding ->
            NavHost(
                navController = navigator.navController,
                startDestination = navigator.startDestination,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                modifier = modifier
                    .background(color = AtsoptTheme.colors.black)
                    .fillMaxSize()
                    .padding(bottom = 66.dp)
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
                    padding = innerPadding,
                    navigateToMyPage = navigator::navigateToMyPage
                )
                shortsNavGraph(
                    padding = innerPadding
                )
                liveNavGraph(
                    padding = innerPadding
                )
                searchNavGraph(
                    padding = innerPadding
                )
                historyNavGraph(
                    padding = innerPadding
                )
                myPageNavGraph(
                    padding = innerPadding,
                    navigateToSignIn = navigator::navigateToSignIn
                )
            }
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(start = 8.dp, end = 8.dp, bottom = 10.dp),
                visible = navigator.setBottomBarVisibility(),
                tabs = MainNavTab.entries.toPersistentList(),
                currentTab = navigator.currentTab,
                onTabSelected = { navigator.navigate(it) }
            )
        }
    )
}
