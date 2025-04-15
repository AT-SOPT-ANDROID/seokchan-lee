package org.sopt.at.history.model

enum class HistoryCategory {
    VIEW_HISTORY,
    PURCHASE_HISTORY,
    FAVORITE_SERIES,
    FAVORITE_MOVIE,
    ;

    companion object {
        fun HistoryCategory.toModel(): String {
            return when (this) {
                VIEW_HISTORY -> "view_history"
                PURCHASE_HISTORY -> "purchase_history"
                FAVORITE_SERIES -> "favorite_series"
                FAVORITE_MOVIE -> "favorite_movie"
            }
        }

        fun String.toCategory(): HistoryCategory {
            return when (this) {
                "purchase_history" -> PURCHASE_HISTORY
                "favorite_series" -> FAVORITE_SERIES
                "favorite_movie" -> FAVORITE_MOVIE
                else -> VIEW_HISTORY
            }
        }
    }
}