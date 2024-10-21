package com.interview.sample.presentation.main_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.interview.common.shared.BaseNavRoutes
import com.interview.onboarding.domain.use_case.OnBoardingUseCases
import com.interview.onboarding.presentation.navigation.OnBoardingGraphRoutes
import com.interview.sample.presentation.navigation.routes.NavBaseGraphRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val onBoardingUseCases: OnBoardingUseCases,
    val player: ExoPlayer
) : ViewModel() {

    private val _startDestination =
        MutableStateFlow<BaseNavRoutes?>(null)
    val startDestination: StateFlow<BaseNavRoutes?> = _startDestination

    init {
        checkOnBoardingStatus()
    }

    private fun checkOnBoardingStatus() = viewModelScope.launch {
        onBoardingUseCases.checkOnBoardingAlreadyDone()
            .collectLatest { shouldStartFromHomeScreen ->
                _startDestination.value =
                    if (shouldStartFromHomeScreen) NavBaseGraphRoutes.HomeGraph else OnBoardingGraphRoutes.OnBoardingGraph
            }
    }

    override fun onCleared() {
        super.onCleared()
        if (player.playbackState != Player.STATE_IDLE) {
            player.release()  // Release player resources when ViewModel is cleared
        }
    }
}