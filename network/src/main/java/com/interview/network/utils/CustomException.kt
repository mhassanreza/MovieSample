package com.interview.network.utils

data class CustomException(
    var errorCode: NetworkErrorType? = NetworkErrorType.UNKNOWN,
    var msgResourceId: Int? = null
) : Exception()