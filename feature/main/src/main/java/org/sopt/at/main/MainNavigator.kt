package org.sopt.at.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.at.history.navigation.navigateHistory
import org.sopt.at.home.navigation.Home
import org.sopt.at.live.navigation.navigateLive
import org.sopt.at.login.navigation.Login
import org.sopt.at.navigation.Route
import org.sopt.at.search.navigation.navigateSearch
import org.sopt.at.shorts.navigation.navigateShorts
import org.sopt.at.signup.navigation.SignUp
import ort.sopt.at.mypage.navigation.MyPage

internal class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Login

    val currentTab: MainNavTab?
        @Composable get() = MainNavTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainNavTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainNavTab.HOME -> {
                navController.popBackStack(MainNavTab.HOME.route, inclusive = true)
                navController.navigate(MainNavTab.HOME.route, navOptions)
            }

            MainNavTab.SHORTS -> navController.navigateShorts(navOptions)
            MainNavTab.LIVE -> navController.navigateLive(navOptions)
            MainNavTab.SEARCH -> navController.navigateSearch(navOptions)
            MainNavTab.HISTORY -> navController.navigateHistory(navOptions)
        }
    }

    @Composable
    fun setBottomBarVisibility() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToSignIn() {
        navController.navigate(Login) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToSignUp() {
        navController.navigate(SignUp) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToHome() {
        navController.navigate(Home) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
    }

    fun navigateToMyPage() {
        navController.navigate(MyPage)
    }

    /*
    fun navigateUpIfNotHome() {
        if (!isSameCurrentDestination<Home>()) {
            navigateUp()
        }
    }*/

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
