package ort.sopt.at.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import org.sopt.at.navigation.Route
import ort.sopt.at.mypage.MyPageRoute

fun NavController.navigateMyPage() {
    navigate(MyPage)
}

fun NavGraphBuilder.myPageNavGraph(
    padding: PaddingValues,
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier
) {
    composable<MyPage> {
        MyPageRoute(
            navigateToSignIn = navigateToSignIn
        )
    }
}

@Serializable
data object MyPage : Route