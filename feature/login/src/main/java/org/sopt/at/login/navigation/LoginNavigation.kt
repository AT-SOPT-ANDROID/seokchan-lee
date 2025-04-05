package org.sopt.at.login.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.login.LoginRoute
import org.sopt.at.navigation.Route

fun NavController.navigateLogin() {
    navigate(Login)
}

fun NavGraphBuilder.loginNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<Login> {
        LoginRoute()
    }
}

@Serializable
data object Login : Route