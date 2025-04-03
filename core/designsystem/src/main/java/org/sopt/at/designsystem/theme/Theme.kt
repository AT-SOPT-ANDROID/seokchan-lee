package org.sopt.at.designsystem.theme

import AtsoptTypography
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Stable
class AtsoptColors(
    white: Color
) {
    var white by mutableStateOf(white)
        private set
}

fun AtsoptColor(
    white: Color = White
) = AtsoptColors(white)

private val LocalAtsoptColors =
    staticCompositionLocalOf<AtsoptColors> { error("provide none color") }

private val LocalAtsoptTypography =
    staticCompositionLocalOf<AtsoptTypography> { error("provide none typography") }

object AtsoptTheme {
    val colors: AtsoptColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAtsoptColors.current

    val typography: AtsoptTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAtsoptTypography.current
}

@Composable
fun provideColorsAndTypography(
    colors: AtsoptColors,
    typography: AtsoptTypography,
    content: @Composable () -> Unit
) {
    val provideColors = remember { colors }
    val provideTypography = remember { typography }

    CompositionLocalProvider(
        LocalAtsoptColors provides colors,
        LocalAtsoptTypography provides typography,
        content = content
    )
}

@Composable
fun FestimateTheme(content: @Composable () -> Unit) {
    val colors = AtsoptColor()
    val typography = AtsoptTypography()
    provideColorsAndTypography(colors, typography) {
        MaterialTheme(content = content)
    }
}
