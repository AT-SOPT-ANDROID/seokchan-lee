package org.sopt.at.live

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import org.sopt.at.designsystem.theme.AtsoptTheme

@Composable
fun LiveRoute(
) {
    LiveScreen()
}

@Composable
fun LiveScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "live",
            color = AtsoptTheme.colors.white
        )
    }
}