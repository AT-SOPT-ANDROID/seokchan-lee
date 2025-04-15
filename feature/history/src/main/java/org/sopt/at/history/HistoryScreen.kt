package org.sopt.at.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import org.sopt.at.designsystem.component.button.AtsoptFloatingActionButton
import org.sopt.at.designsystem.component.dialog.AtsoptBasicDialog
import org.sopt.at.designsystem.component.tabrow.AtsoptTabRow
import org.sopt.at.designsystem.component.textfield.AtsoptBasicTextField
import org.sopt.at.history.component.HistoryPage
import org.sopt.at.history.model.FavoriteDialogState
import org.sopt.at.history.model.HistoryCategory
import org.sopt.at.history.model.HistoryCategory.Companion.toModel
import org.sopt.at.model.BannerInfo

@Composable
fun HistoryRoute(
    viewModel: HistoryViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.favoriteDialogShown) {
        viewModel.apply {
            getFavoriteBanner()
        }
    }

    HistoryScreen(
        favoriteBanners = uiState.favoriteBanners,
        currentCategory = uiState.currentCategory,
        changeCurrentCategory = viewModel::changeCurrentCategory,
        showFavoriteDialog = viewModel::showFavoriteDialog,
        updateDeleteFavoriteTitle = viewModel::updateInputFavoriteTitle
    )
    when (uiState.favoriteDialogShown) {
        FavoriteDialogState.DeleteDialogShown ->
            AtsoptBasicDialog(
                title = "삭제",
                content = "입력",
                cancelButtonText = "취소",
                successButtonText = "확인",
                onClick = { viewModel.setFavoriteBanner(isFavorite = false) },
                onCancelClick = { viewModel.showFavoriteDialog(FavoriteDialogState.UnShown) }
            )

        FavoriteDialogState.InputDialogShown ->
            AtsoptBasicDialog(
                title = "다이얼로그",
                content = "입력",
                cancelButtonText = "취소",
                successButtonText = "확인",
                onClick = { viewModel.setFavoriteBanner(isFavorite = true) },
                onCancelClick = { viewModel.showFavoriteDialog(FavoriteDialogState.UnShown) },
                value = uiState.inputFavoriteTitle,
                onValueChange = viewModel::updateInputFavoriteTitle,
                suffix = { value, onValueChange ->
                    AtsoptBasicTextField(
                        value = value,
                        onValueChange = onValueChange
                    )
                }
            )

        FavoriteDialogState.UnShown -> {}
    }
}

@Composable
fun HistoryScreen(
    favoriteBanners: List<BannerInfo>,
    currentCategory: HistoryCategory,
    changeCurrentCategory: (String) -> Unit,
    showFavoriteDialog: (FavoriteDialogState) -> Unit,
    updateDeleteFavoriteTitle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 4 })
    val coroutineScope = rememberCoroutineScope()

    val categories = listOf(
        "view_history",
        "purchase_history",
        "favorite_series",
        "favorite_movie"
    )

    LaunchedEffect(currentCategory) {
        coroutineScope.launch {
            val page = when (currentCategory) {
                HistoryCategory.VIEW_HISTORY -> 0
                HistoryCategory.PURCHASE_HISTORY -> 1
                HistoryCategory.FAVORITE_SERIES -> 2
                HistoryCategory.FAVORITE_MOVIE -> 3
            }
            if (pagerState.currentPage != page) {
                pagerState.animateScrollToPage(page)
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        val categoryFromPage = when (pagerState.currentPage) {
            0 -> HistoryCategory.VIEW_HISTORY
            1 -> HistoryCategory.PURCHASE_HISTORY
            2 -> HistoryCategory.FAVORITE_SERIES
            3 -> HistoryCategory.FAVORITE_MOVIE
            else -> return@LaunchedEffect
        }

        if (currentCategory != categoryFromPage) {
            changeCurrentCategory(categoryFromPage.toModel())
        }
    }

    Box(
        modifier = modifier
            .padding(top = 50.dp)
            .fillMaxSize()
    ) {
        Column {
            AtsoptTabRow(
                categoryItem = categories,
                changeCategory = { title ->
                    if (currentCategory.toModel() != title) {
                        changeCurrentCategory(title)
                    }
                },
                selectedTabIndex = when (currentCategory) {
                    HistoryCategory.VIEW_HISTORY -> 0
                    HistoryCategory.PURCHASE_HISTORY -> 1
                    HistoryCategory.FAVORITE_SERIES -> 2
                    HistoryCategory.FAVORITE_MOVIE -> 3
                },
                horizontalPadding = 10.dp
            )
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = true,
                verticalAlignment = Alignment.Top
            ) { page ->
                when (page) {
                    2 -> HistoryPage(
                        favoriteBanners = favoriteBanners,
                        updateDeleteFavoriteTitle = updateDeleteFavoriteTitle,
                        onLongClick = { showFavoriteDialog(FavoriteDialogState.DeleteDialogShown) }
                    )
                }
            }
        }
        AtsoptFloatingActionButton(
            onClick = { showFavoriteDialog(FavoriteDialogState.InputDialogShown) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
    }
}
