package org.sopt.at.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.sopt.at.designsystem.component.button.AtsoptBasicTextButton
import org.sopt.at.designsystem.component.textfield.AtsoptBasicTextField
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.extension.clickableWithoutRipple
import org.sopt.at.ui.lifecycle.LaunchedEffectWithLifecycle

@Composable
fun SignUpRoute(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                SignUpSideEffect.NavigateToBack -> navigateToSignIn()
            }
        }
    }

    SignUpScreen(
        modifier = modifier,
        inputId = uiState.inputId,
        inputPassword = uiState.inputPassword,
        updateInputId = viewModel::updateInputId,
        updateInputPassword = viewModel::updateInputPassword,
        navigateToSignIn = viewModel::navigateToBack
    )
}

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    inputId: String,
    inputPassword: String,
    updateInputId: (String) -> Unit,
    updateInputPassword: (String) -> Unit,
    navigateToSignIn: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 2 })
    val focusManager = LocalFocusManager.current

    BackHandler {
        when (pagerState.currentPage) {
            0 -> navigateToSignIn()
            1 -> coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage - 1)
            }
        }
    }

    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .clickableWithoutRipple {
                focusManager.clearFocus(force = true)
            },
    ) {
        Image(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 27.dp)
                .clickableWithoutRipple {
                    when (pagerState.currentPage) {
                        1 -> {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }

                        else -> navigateToSignIn()
                    }
                },
            painter = painterResource(org.sopt.at.designsystem.R.drawable.ic_back_arrow),
            contentDescription = "back"
        )
        Text(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.CenterHorizontally),
            text = when (pagerState.currentPage) {
                0 -> "아이디를 입력해주세요."
                else -> "비밀번호를 입력해주세요."
            },
            style = AtsoptTheme.typography.bodyMedium17.copy(fontSize = 22.sp),
            color = AtsoptTheme.colors.white,
        )
        HorizontalPager(
            modifier = Modifier.weight(1f),
            state = pagerState,
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top,
        ) { page ->
            SignUpPage(
                pagerState.currentPage,
                placeholder = when (pagerState.currentPage) {
                    0 -> "아이디"
                    else -> "비밀번호"
                },
                isPassword = page == 1,
                description = when (pagerState.currentPage) {
                    0 -> "영문 소문자 또는 영문 소문자, 숫자 조합 6 ~ 12 자리"
                    else -> "영문, 숫자, 특수문자(~!@#$%^&*) 조합 8 ~ 15 자리"
                },
                inputId = inputId,
                inputPassword = inputPassword,
                updateInputID = updateInputId,
                updateInputPassword = updateInputPassword,
            )
        }
        AtsoptBasicTextButton(
            modifier = Modifier.padding(bottom = 30.dp),
            text = "다음",
            isActive = when (pagerState.currentPage) {
                0 -> inputId.isNotBlank()
                else -> inputPassword.isNotBlank()
            },
            onClick = {
                when (pagerState.currentPage) {
                    0 -> {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }

                    else -> navigateToSignIn()
                }
            },
            textStyle = AtsoptTheme.typography.bodyMedium15,
            textColor = AtsoptTheme.colors.lightGray,
            backgroundColor = AtsoptTheme.colors.black,
            borderLineColor = AtsoptTheme.colors.lightGray,
        )
    }
}

@Composable
fun SignUpPage(
    currentPage: Int,
    placeholder: String,
    isPassword: Boolean,
    description: String,
    inputId: String,
    inputPassword: String,
    updateInputID: (String) -> Unit,
    updateInputPassword: (String) -> Unit,
) {
    Column {
        AtsoptBasicTextField(
            modifier = Modifier
                .padding(bottom = 15.dp)
                .fillMaxWidth(),
            isPassword = isPassword,
            value = if (currentPage == 0) inputId
            else inputPassword,
            placeholder = placeholder,
            onValueChange = {
                if (currentPage == 0) updateInputID(it)
                else updateInputPassword(it)
            },
        )
        Text(
            text = description,
            style = AtsoptTheme.typography.bodyMedium13,
            color = AtsoptTheme.colors.description
        )
    }
}