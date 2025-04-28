package org.sopt.at.designsystem.component.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.R
import org.sopt.at.designsystem.theme.TextFieldBackground
import org.sopt.at.ui.extension.clickableWithoutRipple

@Composable
fun AtsoptFloatingActionButton(
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(end = 15.dp, bottom = 25.dp)
            .size(56.dp)
            .clip(CircleShape)
            .background(
                color = TextFieldBackground,
                shape = CircleShape
            )
            .clickableWithoutRipple { onClick(true) },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_add_cross),
            contentDescription = "add",
            modifier = Modifier.size(24.dp)
        )
    }
}
