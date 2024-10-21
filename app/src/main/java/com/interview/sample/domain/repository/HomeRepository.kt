package com.interview.sample.domain.repository

import com.interview.domain.home.HomeMovieDto
import com.interview.network.utils.Resource

interface HomeRepository {
    suspend fun getHomeMovieListingData(): Resource<List<HomeMovieDto>>
}