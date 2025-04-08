package org.sopt.at.designsystem.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.theme.AtsoptTheme

@Composable
fun AtsoptBasicSnackBar(
    modifier: Modifier = Modifier,
    message: String,
) {
    Box(
        modifier = modifier
            .imePadding()
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 30.dp),
            color = AtsoptTheme.colors.textFieldBackground,
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = message,
                    style = AtsoptTheme.typography.bodyMedium13,
                    color = AtsoptTheme.colors.white
                )
                IconButton(onClick = {
                    // TODO: 디스미스 클릭
                }) {
                    Icon(Icons.Default.Close, contentDescription = "dismiss")
                }
            }
        }
    }
}