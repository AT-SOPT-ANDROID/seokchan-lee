package org.sopt.at.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.sopt.at.home.navigation.Home
import org.sopt.at.login.navigation.Login
import org.sopt.at.navigation.Route
import org.sopt.at.signup.navigation.SignUp

internal class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Login

    fun navigateBack() {
        navController.popBackStack()
    }

    fun navigateToSignUp() {
        navController.navigate(SignUp)
    }

    fun navigateToHome() {
        navController.navigate(Home) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }
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
