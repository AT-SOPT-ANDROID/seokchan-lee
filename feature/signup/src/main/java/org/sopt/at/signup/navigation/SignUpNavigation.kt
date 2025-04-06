package org.sopt.at.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.Route
import org.sopt.at.signup.SignUpRoute

fun NavController.navigateSignUp() {
    navigate(SignUp)
}

fun NavGraphBuilder.signUpNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<SignUp> {
        SignUpRoute()
    }
}

@Serializable
data object SignUp : Route