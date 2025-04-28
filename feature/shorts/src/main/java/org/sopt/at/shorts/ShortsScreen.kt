package org.sopt.at.shorts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import org.sopt.at.designsystem.theme.AtsoptTheme

@Composable
fun ShortsRoute() {
    ShortsScreen()
}

@Composable
fun ShortsScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "shorts",
            color = AtsoptTheme.colors.white
        )
    }
}
