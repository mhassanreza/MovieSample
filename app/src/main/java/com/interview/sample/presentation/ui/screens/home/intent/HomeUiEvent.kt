package com.interview.sample.presentation.ui.screens.home.intent

/**
 * Login Screen Events
 */
sealed class HomeUiEvent {
    data object GetHomeUpComingEventsData : HomeUiEvent()
}
