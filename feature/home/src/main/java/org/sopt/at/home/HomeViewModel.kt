package org.sopt.at.home

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.domain.usecase.GetBannerUseCase
import org.sopt.at.domain.usecase.PostBannerUseCase
import org.sopt.at.domain.usecase.SetAutoSignInUseCase
import org.sopt.at.home.model.HomeCategory
import org.sopt.at.home.model.HomeCategory.Companion.toCategory
import org.sopt.at.home.model.HomeCategory.Companion.toModel
import org.sopt.at.model.MockBannerData
import org.sopt.at.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val setAutoSignInUseCase: SetAutoSignInUseCase,
    private val postBannerUseCase: PostBannerUseCase,
    private val getBannerUseCase: GetBannerUseCase
) : BaseViewModel<HomeState, HomeSideEffect>(HomeState()) {

    init {
        setAutoSignIn()
        getBanner(HomeCategory.HOME.toModel())
    }

    private fun setAutoSignIn() {
        viewModelScope.launch {
            setAutoSignInUseCase(true)
        }
    }

    fun changeCurrentCategory(category: String) {
        intent {
            copy(
                currentCategory = category.toCategory()
            )
        }
    }

    fun navigateToMyPage() {
        postSideEffect(HomeSideEffect.NavigateMyPage)
    }

    fun getBanner(category: String) {
        viewModelScope.launch {
            getBannerUseCase.invoke(category)
                .onSuccess {
                    intent {
                        copy(
                            banners = it
                        )
                    }
                }
        }
    }

    fun insertBanner() {
        viewModelScope.launch {
            postBannerUseCase(
                listOf(
                    MockBannerData(
                        "home",
                        "언니네 산지직송 2",
                        org.sopt.at.designsystem.R.drawable.img_home_main_banner1.toString()
                    ),
                    MockBannerData(
                        "home",
                        "언젠가는 슬기로울 전공의생활",
                        org.sopt.at.designsystem.R.drawable.img_home_main_banner2.toString()
                    ),
                    MockBannerData(
                        "home",
                        "협상의 기술",
                        org.sopt.at.designsystem.R.drawable.img_home_main_banner3.toString()
                    ),
                    MockBannerData(
                        "home",
                        "바니와 오빠들",
                        org.sopt.at.designsystem.R.drawable.img_home_main_banner4.toString()
                    ),
                    MockBannerData(
                        "home",
                        "신병 3",
                        org.sopt.at.designsystem.R.drawable.img_home_main_banner5.toString()
                    ),
                    MockBannerData(
                        "drama",
                        "그놈은 흑염룡",
                        org.sopt.at.designsystem.R.drawable.img_home_drama_banner1.toString()
                    ),
                    MockBannerData(
                        "entertainment",
                        "탐정들의 영업 비밀",
                        org.sopt.at.designsystem.R.drawable.img_home_entertainment_banner1.toString()
                    ),
                    MockBannerData(
                        "movie",
                        "화사한 그녀",
                        org.sopt.at.designsystem.R.drawable.img_home_movie_banner1.toString()
                    ),
                    MockBannerData(
                        "sports",
                        "베이스볼 tonight",
                        org.sopt.at.designsystem.R.drawable.img_home_sports_banner1.toString()
                    ),
                    MockBannerData(
                        "animation",
                        "꿈빛 파티시엘",
                        org.sopt.at.designsystem.R.drawable.img_home_animation_banner1.toString()
                    ),
                    MockBannerData(
                        "news",
                        "KBS 뉴스 9",
                        org.sopt.at.designsystem.R.drawable.img_home_news_banner1.toString()
                    ),
                    MockBannerData(
                        "entertainment",
                        "아는 형님",
                        org.sopt.at.designsystem.R.drawable.img_home_entertainment_banner2.toString()
                    ),
                    MockBannerData(
                        "entertainment",
                        "김성근의 겨울방학",
                        org.sopt.at.designsystem.R.drawable.img_home_entertainment_banner3.toString()
                    ),
                    MockBannerData(
                        "movie",
                        "대가족",
                        org.sopt.at.designsystem.R.drawable.img_home_movie_banner2.toString()
                    ),
                    MockBannerData(
                        "movie",
                        "플레인",
                        org.sopt.at.designsystem.R.drawable.img_home_movie_banner3.toString()
                    ),
                    MockBannerData(
                        "sports",
                        "비야인드",
                        org.sopt.at.designsystem.R.drawable.img_home_sports_banner2.toString()
                    ),
                    MockBannerData(
                        "sports",
                        "퓨처리그 하이라이트",
                        org.sopt.at.designsystem.R.drawable.img_home_sports_banner3.toString()
                    ),
                    MockBannerData(
                        "animation",
                        "짱구는 못말려 극장판 우리들의 공룡일기",
                        org.sopt.at.designsystem.R.drawable.img_home_animation_banner2.toString()
                    ),
                    MockBannerData(
                        "animation",
                        "짱구는 못말려 24",
                        org.sopt.at.designsystem.R.drawable.img_home_animation_banner3.toString()
                    )
                )
            )
        }
    }
}
