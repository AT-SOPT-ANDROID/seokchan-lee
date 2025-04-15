package org.sopt.at.history.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.model.BannerInfo

@Composable
fun HistoryPage(
    favoriteBanners: List<BannerInfo>,
    updateDeleteFavoriteTitle: (String) -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()

        ) {
            items(favoriteBanners.size) { index ->
                HistoryCell(
                    title = favoriteBanners[index].title,
                    image = favoriteBanners[index].image,
                    updateDeleteFavoriteTitle = updateDeleteFavoriteTitle,
                    onLongClick = onLongClick
                )
            }
        }
    }
}

@Composable
fun HistoryCell(
    title: String,
    image: Int,
    updateDeleteFavoriteTitle: (String) -> Unit,
    onLongClick: () -> Unit,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(6.dp)
) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        updateDeleteFavoriteTitle(title).also {
                            onLongClick()
                        }
                    }
                )
            }
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "image",
            modifier = Modifier
                .padding(bottom = 5.dp)
                .height(200.dp)
                .clip(roundedCornerShape)
                .fillMaxWidth()
        )
        Text(
            text = title,
            style = AtsoptTheme.typography.bodyMedium13,
            color = AtsoptTheme.colors.placeholder
        )
    }
}
