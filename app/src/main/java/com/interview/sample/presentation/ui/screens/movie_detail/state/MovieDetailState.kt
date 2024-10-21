package com.interview.sample.presentation.ui.screens.movie_detail.state

data class MovieDetailState(
    val isDownloaded: Boolean = false,
    val isConnected: Boolean = true,
    val notificationPermissionGranted: Boolean = false
)