package com.interview.sample.presentation.ui.screens.home.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.network.utils.Resource
import com.interview.sample.domain.use_case.home.GetHomeMovieListingUseCase
import com.interview.sample.presentation.ui.screens.home.intent.HomeUiEvent
import com.interview.sample.presentation.ui.screens.home.state.HomeViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getMovieListingUseCases: GetHomeMovieListingUseCase

) : ViewModel() {
    var homeViewState = mutableStateOf(HomeViewState())
        private set

    init {
        fetchUpComingEventsDataFromRepository()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.GetHomeUpComingEventsData -> {
                homeViewState.value = homeViewState.value.copy(
                    isRefreshing = true, upComingEventsList = emptyList()
                )
                fetchUpComingEventsDataFromRepository(showLoader = false)
            }
        }
    }

    private fun fetchUpComingEventsDataFromRepository(showLoader: Boolean = true) =
        viewModelScope.launch {
            getMovieListingUseCases.invoke(showLoader).collect {
                when (it) {
                    is Resource.Loading -> {
                        homeViewState.value = homeViewState.value.copy(
                            isLoading = true, isResponseSuccessful = false
                        )
                    }

                    is Resource.Success -> {
                        homeViewState.value = homeViewState.value.copy(
                            isLoading = false,
                            isRefreshing = false,
                            isResponseSuccessful = true,
                            upComingEventsList = it.data
                        )
                    }

                    is Resource.Error -> TODO()
                }
            }
        }
}