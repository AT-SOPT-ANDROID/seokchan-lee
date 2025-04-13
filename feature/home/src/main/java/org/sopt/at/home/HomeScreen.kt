package org.sopt.at.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.home.component.HomeGenre
import org.sopt.at.home.component.HomeGenreCategory
import org.sopt.at.home.component.HomeLazyRow
import org.sopt.at.home.component.HomeMainBanner
import org.sopt.at.home.component.HomeTopAppBar
import org.sopt.at.ui.lifecycle.LaunchedEffectWithLifecycle
import org.sopt.at.ui.scroll.ScrollHeaderAnimation
import org.sopt.at.ui.scroll.ScrollStickyHeader

@Composable
fun HomeRoute(
    navigateToMyPage: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                HomeSideEffect.NavigateMyPage -> navigateToMyPage()
            }
        }
    }

    HomeScreen(
        navigateToMyPage = viewModel::navigateToMyPage
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigateToMyPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    val headerState = ScrollStickyHeader(listState)

    val mainBannerImage = listOf(
        org.sopt.at.designsystem.R.drawable.img_home_main_banner1,
        org.sopt.at.designsystem.R.drawable.img_home_main_banner2,
        org.sopt.at.designsystem.R.drawable.img_home_main_banner3,
        org.sopt.at.designsystem.R.drawable.img_home_main_banner4,
        org.sopt.at.designsystem.R.drawable.img_home_main_banner5
    )

    val mainGenreImage = listOf(
        org.sopt.at.designsystem.R.drawable.img_home_genre_kbo,
        org.sopt.at.designsystem.R.drawable.img_home_genre_appletv,
        org.sopt.at.designsystem.R.drawable.img_home_genre_kbl,
        org.sopt.at.designsystem.R.drawable.img_home_genre_kids,
        org.sopt.at.designsystem.R.drawable.img_home_genre_ufc
    )

    LazyColumn(
        state = listState,
        modifier = modifier
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            ScrollHeaderAnimation(
                isVisible = headerState.isVisible
            ) {
                HomeTopAppBar(
                    onBroadCastClick = {
                        // TODO.
                    },
                    navigateToMyPage = {
                        navigateToMyPage()
                    }
                )
            }
        }
        stickyHeader {
            HomeGenreCategory()
        }
        item {
            HomeMainBanner(
                mainBanners = mainBannerImage,
                pagerCount = 5
            )
            HomeGenre(
                mainGenre = mainGenreImage
            )
            HomeLazyRow(
                contentImages = mainBannerImage,
                title = "오늘의 티빙 TOP 20",
                itemSpacedBy = 12.dp,
                suffix = { index ->
                    Text(
                        text = (index + 1).toString(),
                        style = AtsoptTheme.typography.titleBold20,
                        color = AtsoptTheme.colors.white,
                        modifier = Modifier.padding(end = 3.dp)
                    )
                }
            )
            HomeLazyRow(
                contentImages = mainBannerImage,
                title = "지금 방영 중인 콘텐츠",
                itemSpacedBy = 16.dp
            )
        }
    }
}
