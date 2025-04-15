package org.sopt.at.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.home.HomeRoute
import org.sopt.at.navigation.Route

fun NavController.navigateHome(navOptions: NavOptions) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToMyPage: () -> Unit
) {
    composable<Home> {
        HomeRoute(
            navigateToMyPage = navigateToMyPage
        )
    }
}

@Serializable
data object Home : Route
