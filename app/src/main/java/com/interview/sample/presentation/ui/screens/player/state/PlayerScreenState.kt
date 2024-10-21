package com.interview.sample.presentation.ui.screens.player.state

data class PlayerScreenState(
    val isBuffering: Boolean = false,
    val isError: String? = null,
    val isPlaying: Boolean = false
)