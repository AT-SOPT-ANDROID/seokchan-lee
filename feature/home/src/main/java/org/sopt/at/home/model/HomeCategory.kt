package org.sopt.at.home.model

enum class HomeCategory {
    HOME,
    DRAMA,
    ENTERTAINMENT,
    MOVIE,
    SPORTS,
    ANIMATION,
    NEWS,
    ;

    companion object {
        fun HomeCategory.toModel(): String {
            return when (this) {
                HOME -> "home"
                DRAMA -> "drama"
                MOVIE -> "movie"
                ENTERTAINMENT -> "entertainment"
                SPORTS -> "sports"
                ANIMATION -> "animation"
                NEWS -> "news"
            }
        }

        fun String.toCategory(): HomeCategory {
            return when (this) {
                "drama" -> DRAMA
                "movie" -> MOVIE
                "entertainment" -> ENTERTAINMENT
                "sports" -> SPORTS
                "animation" -> ANIMATION
                "news" -> NEWS
                else -> HOME
            }
        }
    }
}