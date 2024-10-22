package com.interview.sample.presentation.ui.screens.movie_detail.intent

import androidx.fragment.app.FragmentManager

sealed class MovieDetailIntent {
    data object DownloadStatusUpdated : MovieDetailIntent()
    data class DownloadClicked(val supportFragmentManager: FragmentManager) : MovieDetailIntent()
}