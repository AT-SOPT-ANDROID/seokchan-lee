package org.sopt.at.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.theme.AtsoptTheme

@Composable
fun HomeLazyRow(
    contentImages: List<Int>,
    title: String,
    itemSpacedBy: Dp,
    modifier: Modifier = Modifier,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(6.dp),
    suffix: (@Composable (Int) -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 25.dp)
    ) {
        Text(
            text = title,
            style = AtsoptTheme.typography.titleBold18,
            color = AtsoptTheme.colors.white,
            modifier = Modifier
                .padding(bottom = 12.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(itemSpacedBy)
        ) {
            itemsIndexed(contentImages) { index, contentImages ->
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    suffix?.invoke(index)
                    Image(
                        painter = painterResource(contentImages),
                        contentDescription = "image",
                        modifier = Modifier
                            .height(200.dp)
                            .clip(roundedCornerShape)
                    )
                }
            }
        }
    }
}