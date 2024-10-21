package com.interview.sample.domain.use_case.home

import com.interview.domain.home.HomeMovieDto
import com.interview.network.utils.Resource
import com.interview.sample.domain.repository.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHomeMovieListingUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(showLoader: Boolean = true): Flow<Resource<List<HomeMovieDto>>> =
        flow {
            if (showLoader) emit(Resource.Loading)
            // Add delay to simulate loading time
            delay(500) // Adjust as needed
            emit(homeRepository.getHomeMovieListingData())
        }
}