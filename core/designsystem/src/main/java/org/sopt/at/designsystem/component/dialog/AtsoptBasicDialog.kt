package org.sopt.at.designsystem.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.extension.clickableWithoutRipple

@Composable
fun AtsoptBasicDialog(
    title: String,
    content: String,
    cancelButtonText: String,
    successButtonText: String,
    onClick: () -> Unit,
    onCancelClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(6.dp),
    value: String = "",
    onValueChange: (String) -> Unit = {},
    suffix: (@Composable (value: String, onValueChange: (String) -> Unit) -> Unit)? = null
) {
    Dialog(
        onDismissRequest = { onCancelClick(false) },
        properties = DialogProperties(dismissOnClickOutside = false, dismissOnBackPress = true)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(shape = shape, color = AtsoptTheme.colors.description),
        ) {
            Column(
                modifier = modifier
                    .padding(vertical = 22.dp, horizontal = 40.dp)
            ) {
                Text(
                    text = title,
                    color = AtsoptTheme.colors.black,
                    style = AtsoptTheme.typography.bodyMedium13,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 10.dp, bottom = 6.dp)
                )
                Text(
                    text = content,
                    color = AtsoptTheme.colors.black,
                    style = AtsoptTheme.typography.bodyMedium13,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(42.dp)
                        .padding(bottom = 10.dp)
                )
                suffix?.invoke(value) { onValueChange(it)}
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    DialogButton(
                        modifier = Modifier
                            .clickableWithoutRipple { onCancelClick(false) }
                            .weight(1f),
                        text = cancelButtonText,
                        textColor = AtsoptTheme.colors.black,
                        backgroundColor = AtsoptTheme.colors.lightGray,
                    )
                    DialogButton(
                        text = successButtonText,
                        textColor = AtsoptTheme.colors.black,
                        backgroundColor = AtsoptTheme.colors.lightGray,
                        modifier = Modifier
                            .clickableWithoutRipple {
                                onClick()
                                onCancelClick(false)
                            }
                            .weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun DialogButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    backgroundColor: Color,
) {
    Text(
        text = text,
        color = textColor,
        style = AtsoptTheme.typography.bodyBold15,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(5.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(5.dp),
                color = AtsoptTheme.colors.black
            )
            .padding(vertical = 14.dp, horizontal = 38.dp)
    )
}