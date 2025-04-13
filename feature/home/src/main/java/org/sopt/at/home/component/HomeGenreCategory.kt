package org.sopt.at.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.theme.AtsoptTheme

@Composable
fun HomeGenreCategory(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(AtsoptTheme.colors.black)
            .padding(vertical = 16.dp, horizontal = 30.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "드라마",
            style = AtsoptTheme.typography.bodyBold17,
            color = AtsoptTheme.colors.white
        )
        Text(
            text = "예능",
            style = AtsoptTheme.typography.bodyBold17,
            color = AtsoptTheme.colors.white
        )
        Text(
            text = "영화",
            style = AtsoptTheme.typography.bodyBold17,
            color = AtsoptTheme.colors.white
        )
        Text(
            text = "스포츠",
            style = AtsoptTheme.typography.bodyBold17,
            color = AtsoptTheme.colors.white
        )
        Text(
            text = "애니",
            style = AtsoptTheme.typography.bodyBold17,
            color = AtsoptTheme.colors.white
        )
        Text(
            text = "뉴스",
            style = AtsoptTheme.typography.bodyBold17,
            color = AtsoptTheme.colors.white
        )
    }
}
