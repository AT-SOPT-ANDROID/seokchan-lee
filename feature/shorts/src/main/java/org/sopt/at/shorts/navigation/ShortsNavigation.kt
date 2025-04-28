package org.sopt.at.shorts.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.Route
import org.sopt.at.shorts.ShortsRoute

fun NavController.navigateShorts(navOptions: NavOptions) {
    navigate(Shorts, navOptions)
}

fun NavGraphBuilder.shortsNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<Shorts> {
        ShortsRoute()
    }
}

@Serializable
data object Shorts : Route
