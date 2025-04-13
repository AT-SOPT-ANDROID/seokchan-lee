package org.sopt.at.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import org.sopt.at.designsystem.theme.AtsoptTheme

@Composable
fun HistoryRoute(
) {
    HistoryScreen()
}

@Composable
fun HistoryScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "history",
            color = AtsoptTheme.colors.white
        )
    }
}