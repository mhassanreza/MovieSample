package com.interview.sample.presentation.ui.screens.player.intent

sealed class PlayerScreenIntent {
    data class SetAutoPlayOnPrepare(val allow: Boolean) : PlayerScreenIntent()
    data object PlayVideo : PlayerScreenIntent()
    data object PauseVideo : PlayerScreenIntent()
    data object ErrorHandled : PlayerScreenIntent()
}