package org.sopt.at.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.home.HomeRoute
import org.sopt.at.navigation.Route

fun NavController.navigateHome() {
    navigate(Home)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit
) {
    composable<Home> {
        HomeRoute(
            navigateToSignIn = navigateToSignIn
        )
    }
}

@Serializable
data object Home : Route
