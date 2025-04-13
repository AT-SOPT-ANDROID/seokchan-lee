package org.sopt.at.ui.scroll

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow

@Composable
fun ScrollStickyHeader(
    listState: LazyListState
): ScrollHeaderState {
    var lastScrollPosition by remember { mutableFloatStateOf(0f) }
    var isHeaderVisible by remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { scrollOffset ->
                val currentScroll = scrollOffset.toFloat()
                val scrollDirection = currentScroll - lastScrollPosition
                isHeaderVisible = when {
                    scrollDirection < 0 -> true
                    scrollDirection > 0 && currentScroll > 100f -> false
                    else -> isHeaderVisible
                }
                lastScrollPosition = currentScroll
            }
    }
    return remember(isHeaderVisible) {
        ScrollHeaderState(isHeaderVisible)
    }
}

@Composable
fun ScrollHeaderAnimation(
    isVisible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(
            initialOffsetY = { -it },
            animationSpec = tween(300)
        ),
        exit = slideOutVertically(
            targetOffsetY = { -it },
            animationSpec = tween(300)
        )
    ) {
        content()
    }
}

@Stable
class ScrollHeaderState(
    val isVisible: Boolean
)
