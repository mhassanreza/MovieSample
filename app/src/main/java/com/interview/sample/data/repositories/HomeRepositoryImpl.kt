package com.interview.sample.data.repositories

import com.interview.domain.home.HomeMovieDto
import com.interview.network.R
import com.interview.network.services.HomeService
import com.interview.network.utils.CustomException
import com.interview.network.utils.NetworkErrorType
import com.interview.network.utils.Resource
import com.interview.sample.domain.repository.HomeRepository
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {

    override suspend fun getHomeMovieListingData(): Resource<List<HomeMovieDto>> {
        return try {
            val response = homeService.getHomeMovieListingData()
            response
        } catch (e: Exception) {
            Resource.Error(
                CustomException(
                    NetworkErrorType.UNKNOWN,
                    R.string.err_msg_unknown_error_occured
                )
            )
        }
    }

}