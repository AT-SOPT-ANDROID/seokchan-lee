package org.sopt.at.history.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.history.HistoryRoute
import org.sopt.at.navigation.Route

fun NavController.navigateHistory() {
    navigate(History)
}

fun NavGraphBuilder.historyNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<History> {
        HistoryRoute()
    }
}

@Serializable
data object History : Route
