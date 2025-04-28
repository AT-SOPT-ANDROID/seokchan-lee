package org.sopt.at.home.model

enum class HomeCategory(
    val index: Int,
    val text: String,
    val rankingTitle: String,
    val subtitle: String
) {
    HOME(-1, "home", "오늘의 티빙 TOP 20", "지금 방영 중인 콘텐츠"),
    DRAMA(0, "drama", "실시간 인기 드라마", "오직 티빙에서"),
    ENTERTAINMENT(1, "entertainment", "실시간 인기 예능", "예능 시리즈"),
    MOVIE(2, "movie", "실시간 인기 영화", "추천 급상승 영화"),
    SPORTS(3, "sports", "2025 KBO 리그 중계", "KBO 하이라이트"),
    ANIMATION(4, "animation", "실시간 인기 애니메이션", "일상의 즐거움"),
    NEWS(5, "news", "24시간 보도 채널 ON-AIR", "정치/시사")
    ;

    companion object {
        fun toHomeCategory(text: String): HomeCategory {
            return entries.first { it.text == text }
        }
    }
}
