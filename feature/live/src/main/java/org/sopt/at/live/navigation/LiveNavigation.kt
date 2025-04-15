package org.sopt.at.live.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.live.LiveRoute
import org.sopt.at.navigation.Route

fun NavController.navigateLive(navOptions: NavOptions) {
    navigate(Live, navOptions)
}

fun NavGraphBuilder.liveNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<Live> {
        LiveRoute()
    }
}

@Serializable
data object Live : Route
