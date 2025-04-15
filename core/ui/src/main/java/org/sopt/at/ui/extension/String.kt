package org.sopt.at.ui.extension

fun String.toCategoryKor(): String {
    return when (this) {
        "drama" -> "드라마"
        "movie" -> "영화"
        "entertainment" -> "예능"
        "sports" -> "스포츠"
        "animation" -> "애니"
        "news" -> "뉴스"
        "view_history" -> "시청내역"
        "purchase_history" -> "구매내역"
        "favorite_series" -> "찜한 시리즈"
        "favorite_movie" -> "찜한 영화"
        else -> "홈"
    }
}
