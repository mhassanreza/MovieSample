package com.interview.sample.presentation.ui.screens.player.viewmodel

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerScreenViewModel @Inject constructor(
    val player: ExoPlayer
) : ViewModel() {

}