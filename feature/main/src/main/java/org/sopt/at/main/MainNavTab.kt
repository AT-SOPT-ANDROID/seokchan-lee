package org.sopt.at.main

import androidx.compose.runtime.Composable
import org.sopt.at.history.navigation.History
import org.sopt.at.home.navigation.Home
import org.sopt.at.live.navigation.Live
import org.sopt.at.navigation.Route
import org.sopt.at.search.navigation.Search
import org.sopt.at.shorts.navigation.Shorts

internal enum class MainNavTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: Route
) {
    HOME(
        iconResId = org.sopt.at.designsystem.R.drawable.ic_bottom_nav_home,
        contentDescription = "HOME",
        Home
    ),
    SHORTS(
        iconResId = org.sopt.at.designsystem.R.drawable.ic_bottom_nav_shorts,
        contentDescription = "Shorts",
        Shorts
    ),
    LIVE(
        iconResId = org.sopt.at.designsystem.R.drawable.ic_bottom_nav_live,
        contentDescription = "LIVE",
        Live
    ),
    SEARCH(
        iconResId = org.sopt.at.designsystem.R.drawable.ic_bottom_nav_search,
        contentDescription = "SEARCH",
        Search
    ),
    HISTORY(
        iconResId = org.sopt.at.designsystem.R.drawable.ic_bottom_nav_history,
        contentDescription = "HISTORY",
        History
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (Route) -> Boolean): MainNavTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
