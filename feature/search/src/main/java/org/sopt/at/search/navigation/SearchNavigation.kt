package org.sopt.at.search.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.Route
import org.sopt.at.search.SearchRoute

fun NavController.navigateSearch() {
    navigate(Search)
}

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues,
    modifier: Modifier = Modifier
) {
    composable<Search> {
        SearchRoute()
    }
}

@Serializable
data object Search : Route
