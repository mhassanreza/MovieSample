package com.interview.sample.presentation.ui.screens.home.state

import com.interview.domain.home.HomeMovieDto
import com.interview.network.utils.CustomException

/**
 * HomeViewState State holding ui input values
 */
data class HomeViewState(
    val errorState: CustomException = CustomException(),
    val isResponseSuccessful: Boolean = false,
    val isLoading: Boolean = false,
    var isException: Boolean = false,
    var isRefreshing: Boolean = false,
    val exception: CustomException? = null,
    val upComingEventsList: List<HomeMovieDto> = emptyList()
)


