package org.sopt.at.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun HomeMainBanner(
    mainBanners: List<Int>,
    pagerCount: Int,
    modifier: Modifier = Modifier,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(8.dp),
    contentPadding: Dp = 30.dp,
    pageSpacing: Dp = 20.dp
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { pagerCount })

    HorizontalPager(
        state = pagerState,
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = contentPadding),
        pageSpacing = pageSpacing
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            Image(
                painter = painterResource(mainBanners[page]),
                contentDescription = "banner",
                modifier = Modifier
                    .clip(roundedCornerShape)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}
