package org.sopt.at.ui.effect

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.delay

suspend fun shakeAnimation(shakeOffset: MutableState<Float>) {
    val shakeValues = listOf(0.7f, -0.7f, 0.7f, -0.7f, 0f)
    val delays = listOf(50L, 50L, 50L, 100L, 0L)

    shakeValues.zip(delays).forEach { (value, delayMs) ->
        shakeOffset.value = value
        if (delayMs > 0) delay(delayMs)
    }
}