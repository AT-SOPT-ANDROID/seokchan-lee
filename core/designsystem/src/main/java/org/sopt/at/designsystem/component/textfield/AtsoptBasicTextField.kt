package org.sopt.at.designsystem.component.textfield

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.R
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.designsystem.theme.White
import org.sopt.at.ui.extension.clickableWithoutRipple

@Composable
fun AtsoptBasicTextField(
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    shape: Shape = RoundedCornerShape(4.dp),
    placeholder: String = "",
    labelText: String = "",
    value: String = "",
    onValueChange: (String) -> Unit = { _ -> },
    isError: Boolean = false,
    maxLines: Int = 1,
    minLines: Int = 1,
    maxLength: Int = 10,
    minHeight: Dp = 52.dp,
    textStyle: TextStyle = AtsoptTheme.typography.bodyMedium15,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    highlightOnFocus: Boolean = true,
) {
    val isFocused by interactionSource.collectIsFocusedAsState()

    val borderLineColor = when {
        isFocused -> AtsoptTheme.colors.lightGray
        else -> AtsoptTheme.colors.textFieldBackground
    }

    val labelTextColor = when {
        isError -> Color.Red
        else -> Color.Gray
    }
    var isPasswordVisible by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newValue ->
            if (newValue.replace(" ", "").length <= maxLength) onValueChange(newValue)
        },
        singleLine = maxLines == 1,
        textStyle = textStyle.copy(AtsoptTheme.colors.white),
        maxLines = if (minLines > maxLines) minLines else maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(White),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = when {
            !isPassword -> VisualTransformation.None
            isPasswordVisible -> VisualTransformation.None
            else -> PasswordVisualTransformation()
        },
        decorationBox = { innerText ->
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .heightIn(minHeight)
                        .fillMaxWidth()
                        .clip(shape = shape)
                        .background(color = AtsoptTheme.colors.textFieldBackground)
                        .border(
                            width = 1.dp,
                            color = borderLineColor,
                            shape = shape,
                        )
                        .padding(vertical = 16.dp, horizontal = 18.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    color = AtsoptTheme.colors.placeholder,
                                    style = AtsoptTheme.typography.bodyMedium15,
                                    maxLines = 1,
                                    overflow = TextOverflow.Clip,
                                )
                            }
                            innerText()
                        }
                        if (isPassword) {
                            Image(
                                modifier = Modifier
                                    .clickableWithoutRipple {
                                        isPasswordVisible = !isPasswordVisible
                                    },
                                painter = if (isPasswordVisible)
                                    painterResource(R.drawable.ic_password_visible)
                                else
                                    painterResource(R.drawable.ic_password_invisible),
                                contentDescription = "show password",
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TextFieldPreview() {
    Column {
        AtsoptBasicTextField()
    }
}