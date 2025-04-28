package org.sopt.at.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.sopt.at.designsystem.component.tabrow.AtsoptTabRow
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.home.component.HomeGenre
import org.sopt.at.home.component.HomeLazyRow
import org.sopt.at.home.component.HomeMainBanner
import org.sopt.at.home.component.HomeTopAppBar
import org.sopt.at.home.model.HomeCategory
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

    LaunchedEffect(uiState.currentCategory) {
        viewModel.getBanner(uiState.currentCategory.text)
    }

    HomeScreen(
        homeBanners = uiState.banners.map { it.image },
        currentCategory = uiState.currentCategory,
        changeCategory = viewModel::changeCurrentCategory,
        navigateToMyPage = viewModel::navigateToMyPage
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    homeBanners: List<Int>,
    currentCategory: HomeCategory,
    changeCategory: (String) -> Unit,
    navigateToMyPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    var scrollToTop by remember { mutableStateOf(false) }
    val headerState = ScrollStickyHeader(listState)

    val categories = listOf(
        "drama",
        "entertainment",
        "movie",
        "sports",
        "animation",
        "news"
    )

    val mainGenreImage = listOf(
        org.sopt.at.designsystem.R.drawable.img_home_genre_kbo,
        org.sopt.at.designsystem.R.drawable.img_home_genre_appletv,
        org.sopt.at.designsystem.R.drawable.img_home_genre_kbl,
        org.sopt.at.designsystem.R.drawable.img_home_genre_kids,
        org.sopt.at.designsystem.R.drawable.img_home_genre_ufc
    )

    LaunchedEffect(scrollToTop) {
        if (scrollToTop) {
            listState.animateScrollToItem(0)
            scrollToTop = false
        }
    }

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
            AtsoptTabRow(
                categoryItem = categories,
                changeCategory = { category ->
                    changeCategory(category)
                    if (!scrollToTop) {
                        scrollToTop = true
                    }
                },
                selectedTabIndex = currentCategory.index
            )
        }
        item {
            HomeMainBanner(
                mainBanners = homeBanners,
                pagerCount = homeBanners.size
            )
            HomeGenre(
                mainGenre = mainGenreImage
            )
            HomeLazyRow(
                contentImages = homeBanners,
                title = currentCategory.rankingTitle,
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
                contentImages = homeBanners,
                title = currentCategory.subtitle,
                itemSpacedBy = 16.dp
            )
        }
    }
}
