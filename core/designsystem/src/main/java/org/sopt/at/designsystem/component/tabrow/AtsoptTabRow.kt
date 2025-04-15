package org.sopt.at.designsystem.component.tabrow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.sopt.at.designsystem.theme.AtsoptTheme
import org.sopt.at.ui.extension.clickableWithoutRipple
import org.sopt.at.ui.extension.toCategoryKor

@Composable
fun AtsoptTabRow(
    categoryItem: List<String>,
    changeCategory: (String) -> Unit,
    selectedTabIndex: Int,
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = 0.dp,
) {
    var textWidth by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .background(AtsoptTheme.colors.black)
            .padding(vertical = 13.dp, horizontal = horizontalPadding),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            categoryItem.forEachIndexed { index, title ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = title.toCategoryKor(),
                        style = if (selectedTabIndex == index) AtsoptTheme.typography.bodyBold17 else AtsoptTheme.typography.bodyMedium17,
                        color = if (selectedTabIndex == index) AtsoptTheme.colors.white else AtsoptTheme.colors.textFieldBorder,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickableWithoutRipple {
                                changeCategory(title)
                            }
                            .padding(horizontal = 10.dp)
                            .onGloballyPositioned { coordinates ->
                                textWidth = coordinates.size.width
                            }
                    )
                }
            }
        }
    }
}