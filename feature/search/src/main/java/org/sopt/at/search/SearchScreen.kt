package org.sopt.at.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.sopt.at.designsystem.component.textfield.AtsoptBasicTextField
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.lifecycle.LaunchedEffectWithLifecycle

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                else -> {}
            }
        }
    }

    SearchScreen(
        inputKeyword = uiState.keyword,
        userNickname = uiState.userNickname,
        updateSearchKeyword = viewModel::updateSearchKeyword
    )
}

@Composable
fun SearchScreen(
    inputKeyword: String,
    userNickname: List<String>,
    updateSearchKeyword: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 50.dp)
    ) {
        AtsoptBasicTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
            value = inputKeyword,
            placeholder = "닉네임 검색",
            onValueChange = updateSearchKeyword
        )
        LazyColumn {
            items(userNickname) {
                Text(
                    text = it,
                    color = AtsoptTheme.colors.white
                )
            }
        }
    }
}
