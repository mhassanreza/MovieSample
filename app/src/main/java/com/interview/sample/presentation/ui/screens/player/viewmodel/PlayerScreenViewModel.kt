package com.interview.sample.presentation.ui.screens.player.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.interview.sample.presentation.ui.screens.movie_detail.intent.MovieDetailIntent
import com.interview.sample.presentation.ui.screens.movie_detail.state.MovieDetailState
import com.interview.sample.presentation.ui.screens.player.intent.PlayerScreenIntent
import com.interview.sample.presentation.ui.screens.player.state.PlayerScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerScreenViewModel @Inject constructor(
    val player: ExoPlayer
) : ViewModel() {
    var playerScreenViewState = mutableStateOf(PlayerScreenState())
        private set


    init {
        setupPlayerListener()
    }

    private fun setupPlayerListener() {
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                val isBuffering = state == Player.STATE_BUFFERING
                playerScreenViewState.value = playerScreenViewState.value.copy(
                    isBuffering = isBuffering
                )
            }

            override fun onPlayerError(error: PlaybackException) {
                val errorMessage =
                    if (error.errorCode == PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED || error.errorCode == PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED) {
                        "DRM Error: ${error.message}"
                    } else {
                        error.message
                    }
                playerScreenViewState.value = playerScreenViewState.value.copy(
                    isError = errorMessage
                )
            }
        })
    }

    fun handleIntent(intent: PlayerScreenIntent) {
        when (intent) {
            PlayerScreenIntent.PlayVideo -> {
                player.playWhenReady = true
                playerScreenViewState.value = playerScreenViewState.value.copy(
                    isPlaying = true
                )
            }

            PlayerScreenIntent.PauseVideo -> {
                player.playWhenReady = false
                playerScreenViewState.value = playerScreenViewState.value.copy(
                    isPlaying = false
                )
            }

            PlayerScreenIntent.ErrorHandled -> {
                playerScreenViewState.value = playerScreenViewState.value.copy(
                    isError = null
                )
            }

            is PlayerScreenIntent.SetAutoPlayOnPrepare -> {
                player.playWhenReady = intent.allow
            }
        }
    }
}