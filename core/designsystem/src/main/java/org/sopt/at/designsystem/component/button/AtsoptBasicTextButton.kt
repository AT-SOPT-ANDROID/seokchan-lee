package org.sopt.at.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.extension.clickableWithoutRipple

@Composable
fun AtsoptBasicTextButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    textStyle: TextStyle = AtsoptTheme.typography.bodyMedium15,
    textColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified,
    borderLineColor: Color = Color.Unspecified,
    minHeight: Dp = 52.dp,
) {
    Column(
        modifier = modifier
            .heightIn(minHeight)
            .fillMaxWidth()
            .clip(shape = shape)
            .background(if (isActive) AtsoptTheme.colors.buttonSuccess else backgroundColor)
            .border(
                width = 1.dp,
                color = if (isActive) Color.Unspecified else borderLineColor,
                shape = shape,
            )
            .clickableWithoutRipple(
                enabled = isActive,
            ) { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(PaddingValues(vertical = 10.dp)),
            text = text,
            style = textStyle,
            color = if (isActive) AtsoptTheme.colors.white else textColor,
        )
    }
}