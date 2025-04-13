package org.sopt.at.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.at.ui.extension.clickableWithoutRipple

@Composable
fun HomeTopAppBar(
    onBroadCastClick: () -> Unit,
    navigateToMyPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(org.sopt.at.designsystem.R.drawable.ic_app_logo),
            contentDescription = "appLogo"
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(org.sopt.at.designsystem.R.drawable.ic_home_broadcast),
            contentDescription = "appLogo",
            modifier
                .padding(end = 25.dp)
                .clickableWithoutRipple { onBroadCastClick() }
        )
        Image(
            painter = painterResource(org.sopt.at.designsystem.R.drawable.img_my_profile),
            contentDescription = "appLogo",
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .clickableWithoutRipple { navigateToMyPage() }
        )
    }
}
