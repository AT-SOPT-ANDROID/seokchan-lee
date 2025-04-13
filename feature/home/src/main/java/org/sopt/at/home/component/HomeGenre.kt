package org.sopt.at.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.theme.AtsoptTheme

@Composable
fun HomeGenre(
    mainGenre: List<Int>,
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(horizontal = 17.dp, vertical = 8.dp),
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(6.dp),
    topColor: Color = AtsoptTheme.colors.homeGenre1,
    bottomColor: Color = AtsoptTheme.colors.homeGenre2,
) {
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(topColor, bottomColor)
    )

    LazyRow(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(mainGenre) { genreImage ->
            Box(
                modifier = Modifier
                    .clip(roundedCornerShape)
                    .background(gradientBackground)
                    .padding(innerPadding),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(genreImage),
                    contentDescription = "genre",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}