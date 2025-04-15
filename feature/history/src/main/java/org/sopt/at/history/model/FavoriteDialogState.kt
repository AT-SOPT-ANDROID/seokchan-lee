package org.sopt.at.history.model

sealed interface FavoriteDialogState {
    data object UnShown : FavoriteDialogState
    data object InputDialogShown : FavoriteDialogState
    data object DeleteDialogShown : FavoriteDialogState
}