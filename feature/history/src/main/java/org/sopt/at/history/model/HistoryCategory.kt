package org.sopt.at.history.model

enum class HistoryCategory(val index: Int, val text: String) {
    VIEW_HISTORY(0, "view_history"),
    PURCHASE_HISTORY(1, "purchase_history"),
    FAVORITE_SERIES(2, "favorite_series"),
    FAVORITE_MOVIE(3, "favorite_movie")
    ;

    companion object {
        fun toHistoryCategory(text: String): HistoryCategory {
            return entries.first { it.text == text }
        }

        fun toHistoryCategory(index: Int): HistoryCategory {
            return entries.first { it.index == index }
        }
    }
}
