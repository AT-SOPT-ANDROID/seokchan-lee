package org.sopt.at.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import org.sopt.at.designsystem.component.button.AtsoptBasicTextButton
import org.sopt.at.designsystem.component.textfield.AtsoptBasicTextField
import org.sopt.at.designsystem.snackbar.AtsoptBasicSnackBar
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.effect.shakeAnimation
import org.sopt.at.ui.extension.clickableWithoutRipple
import org.sopt.at.ui.lifecycle.LaunchedEffectWithLifecycle

@Composable
fun LoginRoute(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showSnackBar by remember { mutableStateOf(false) }
    val shakeOffset = remember { mutableFloatStateOf(0f) }

    LaunchedEffectWithLifecycle {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                LoginSideEffect.NavigateSignUp -> navigateToSignUp()
                LoginSideEffect.NavigateHome -> navigateToHome()
                LoginSideEffect.SignInFailure -> {
                    showSnackBar = true
                    shakeAnimation(shakeOffset)
                    delay(1700)
                    showSnackBar = false
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        shakeOffset = shakeOffset.floatValue,
        updateUserID = viewModel::updateUserID,
        updateUserPassword = viewModel::updateUserPassword,
        navigateToSignUp = viewModel::navigateToSignUp,
        navigateToHome = viewModel::navigateToHome,
        modifier = modifier
    )
    if (showSnackBar) {
        AtsoptBasicSnackBar(
            message = "회원 정보 없음"
        )
    }
}

@Composable
fun LoginScreen(
    uiState: LoginState,
    shakeOffset: Float,
    updateUserID: (String) -> Unit,
    updateUserPassword: (String) -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier
            .fillMaxSize()
            .imePadding()
            .offset(x = shakeOffset.dp)
            .padding(horizontal = 10.dp)
            .clickableWithoutRipple {
                focusManager.clearFocus(force = true)
            }
    ) {
        Image(
            painter = painterResource(org.sopt.at.designsystem.R.drawable.ic_back_arrow),
            contentDescription = "back",
            modifier = Modifier.padding(top = 50.dp, bottom = 50.dp)
        )
        Text(
            text = "TVING ID 로그인",
            style = AtsoptTheme.typography.titleBold20,
            color = AtsoptTheme.colors.white,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        AtsoptSignUpBody(
            focusManager = focusManager,
            userId = uiState.id,
            userPassword = uiState.password,
            updateUserID = updateUserID,
            updateUserPassword = updateUserPassword,
            navigateToHome = navigateToHome,
            modifier = Modifier.padding(bottom = 35.dp)
        )
        AtsoptSignUpFooter(
            navigateToSignUp = navigateToSignUp
        )
    }
}

@Composable
private fun AtsoptSignUpBody(
    focusManager: FocusManager,
    userId: String,
    userPassword: String,
    updateUserID: (String) -> Unit,
    updateUserPassword: (String) -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        AtsoptBasicTextField(

            value = userId,
            placeholder = "아이디",
            onValueChange = {
                updateUserID(it)
            },
            modifier = Modifier
                .padding(bottom = 13.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )
        AtsoptBasicTextField(

            isPassword = true,
            value = userPassword,
            placeholder = "비밀번호",
            onValueChange = {
                updateUserPassword(it)
            },
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(force = true) }
            )
        )
        AtsoptBasicTextButton(
            text = "로그인하기",
            isActive = userId.isNotBlank() && userPassword.isNotBlank(),
            onClick = navigateToHome,
            textStyle = AtsoptTheme.typography.titleBold18,
            textColor = AtsoptTheme.colors.buttonText,
            backgroundColor = AtsoptTheme.colors.buttonBackground
        )
    }
}

@Composable
private fun AtsoptSignUpFooter(
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
    ) {
        Text(
            text = "아이디 찾기",
            style = AtsoptTheme.typography.bodyMedium15,
            color = AtsoptTheme.colors.lightGray
        )
        VerticalDivider(
            thickness = 1.dp,
            color = AtsoptTheme.colors.placeholder,
            modifier = Modifier.fillMaxHeight()
        )
        Text(
            text = "비밀번호 찾기",
            style = AtsoptTheme.typography.bodyMedium15,
            color = AtsoptTheme.colors.lightGray
        )
        VerticalDivider(
            thickness = 1.dp,
            color = AtsoptTheme.colors.placeholder,
            modifier = Modifier.fillMaxHeight()
        )
        Text(
            text = "회원가입",
            style = AtsoptTheme.typography.bodyMedium15,
            color = AtsoptTheme.colors.lightGray,
            modifier = Modifier.clickableWithoutRipple {
                navigateToSignUp()
            }
        )
    }
}
