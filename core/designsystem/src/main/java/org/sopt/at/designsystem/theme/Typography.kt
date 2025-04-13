import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sopt.at.designsystem.R

val PretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val PretendardExtraBold = FontFamily(Font(R.font.pretendard_extrabold, FontWeight.ExtraBold))
val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))

@Stable
class AtsoptTypography internal constructor(
    titleExtra24: TextStyle,
    titleExtra20: TextStyle,
    titleBold20: TextStyle,
    titleBold18: TextStyle,
    bodyBold17: TextStyle,
    bodySemibold17: TextStyle,
    bodyMedium17: TextStyle,
    bodyBold15: TextStyle,
    bodySemibold15: TextStyle,
    bodyMedium15: TextStyle,
    bodySemibold13: TextStyle,
    bodyMedium13: TextStyle,
    capRegular11: TextStyle
) {
    var titleExtra24: TextStyle by mutableStateOf(titleExtra24)
        private set
    var titleExtra20: TextStyle by mutableStateOf(titleExtra20)
        private set
    var titleBold20: TextStyle by mutableStateOf(titleBold20)
        private set
    var titleBold18: TextStyle by mutableStateOf(titleBold18)
        private set
    var bodyBold17: TextStyle by mutableStateOf(bodyBold17)
        private set
    var bodySemibold17: TextStyle by mutableStateOf(bodySemibold17)
        private set
    var bodyMedium17: TextStyle by mutableStateOf(bodyMedium17)
        private set
    var bodyBold15: TextStyle by mutableStateOf(bodyBold15)
        private set
    var bodySemibold15: TextStyle by mutableStateOf(bodySemibold15)
        private set
    var bodyMedium15: TextStyle by mutableStateOf(bodyMedium15)
        private set
    var bodySemibold13: TextStyle by mutableStateOf(bodySemibold13)
        private set
    var bodyMedium13: TextStyle by mutableStateOf(bodyMedium13)
        private set
    var capRegular11: TextStyle by mutableStateOf(capRegular11)
        private set
}

fun AtsoptTypography(): AtsoptTypography {
    return AtsoptTypography(
        titleExtra24 = TextStyle(
            fontFamily = PretendardExtraBold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        ),
        titleExtra20 = TextStyle(
            fontFamily = PretendardExtraBold,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        titleBold20 = TextStyle(
            fontFamily = PretendardBold,
            fontSize = 20.sp,
            lineHeight = 28.sp
        ),
        titleBold18 = TextStyle(
            fontFamily = PretendardBold,
            fontSize = 18.sp,
            lineHeight = 25.sp
        ),
        bodyBold17 = TextStyle(
            fontFamily = PretendardBold,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        bodySemibold17 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        bodyMedium17 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 17.sp,
            lineHeight = 24.sp
        ),
        bodyBold15 = TextStyle(
            fontFamily = PretendardBold,
            fontSize = 15.sp,
            lineHeight = 21.sp
        ),
        bodySemibold15 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 15.sp,
            lineHeight = 21.sp
        ),
        bodyMedium15 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 15.sp,
            lineHeight = 21.sp
        ),
        bodySemibold13 = TextStyle(
            fontFamily = PretendardSemiBold,
            fontSize = 13.sp,
            lineHeight = 18.sp
        ),
        bodyMedium13 = TextStyle(
            fontFamily = PretendardMedium,
            fontSize = 13.sp,
            lineHeight = 18.sp
        ),
        capRegular11 = TextStyle(
            fontFamily = PretendardRegular,
            fontSize = 11.sp,
            lineHeight = 15.sp
        )
    )
}
