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
    white: Color,
    black: Color,
    placeholder: Color,
    textFieldBackground: Color,
    textFieldBorder: Color,
    buttonText: Color,
    buttonBackground: Color,
    lightGray: Color,
    description: Color,
) {
    var white by mutableStateOf(white)
        private set
    var black by mutableStateOf(black)
        private set
    var placeholder by mutableStateOf(placeholder)
        private set
    var textFieldBackground by mutableStateOf(textFieldBackground)
        private set
    var textFieldBorder by mutableStateOf(textFieldBorder)
        private set
    var buttonText by mutableStateOf(buttonText)
        private set
    var buttonBackground by mutableStateOf(buttonBackground)
        private set
    var lightGray by mutableStateOf(lightGray)
        private set
    var description by mutableStateOf(description)
        private set
}

fun AtsoptColor(
    white: Color = White,
    black: Color = Black,
    placeholder: Color = Placeholder,
    textFieldBackground: Color = TextFieldBackground,
    textFieldBorder: Color = TextFieldBorder,
    buttonText: Color = ButtonText,
    buttonBackground: Color = ButtonBackground,
    lightGray: Color = LightGray,
    description: Color = Description,
) = AtsoptColors(
    white,
    black,
    placeholder,
    textFieldBackground,
    textFieldBorder,
    buttonText,
    buttonBackground,
    lightGray,
    description,
)

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
