package com.interview.network.utils

import com.interview.common.utils.StatusCode
import com.interview.network.R
import retrofit2.HttpException
import java.net.UnknownHostException

sealed class Resource<out T> {
    data object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val customException: CustomException
    ) : Resource<Nothing>()
}

object ResourceFilterUtil {
    fun filterResource(
        e: Exception
    ): CustomException {
        return when (e) {
            is UnknownHostException -> CustomException(
                NetworkErrorType.NO_INTERNET,
                R.string.err_msg_internet_not_connected
            )

            is java.net.SocketTimeoutException -> CustomException(
                NetworkErrorType.TIMEOUT, R.string.err_msg_request_timeout
            )

            is HttpException -> handleHttpException(e.code())

            else -> CustomException(
                NetworkErrorType.UNKNOWN, R.string.err_msg_unknown_error_occured
            )
        }
    }

    private fun handleHttpException(
        errorCode: Int
    ): CustomException {
        return when (errorCode) {
            StatusCode.Unauthorized.code -> CustomException(
                NetworkErrorType.UNAUTHORIZED,
                R.string.err_msg_unauthorized
            )

            StatusCode.RequestTimeout.code -> CustomException(
                NetworkErrorType.TIMEOUT,
                R.string.err_msg_request_timeout
            )

            StatusCode.InternalServerError.code -> CustomException(
                NetworkErrorType.SERVER_ERROR,
                R.string.err_msg_internal_server_error
            )

            StatusCode.ServiceUnavailable.code -> CustomException(
                NetworkErrorType.UNKNOWN, R.string.err_msg_unknwon_host
            )

            StatusCode.BadRequest.code -> CustomException(
                NetworkErrorType.UNKNOWN, R.string.err_msg_bad_request
            )

            StatusCode.Forbidden.code -> CustomException(
                NetworkErrorType.UNKNOWN, R.string.err_msg_forbidden
            )

            StatusCode.MethodNotAllowed.code -> CustomException(
                NetworkErrorType.UNKNOWN,
                R.string.err_msg_method_not_allowed
            )

            StatusCode.UnsupportedMediaType.code -> CustomException(
                NetworkErrorType.UNKNOWN,
                R.string.err_msg_unsupported_media_file
            )

            else -> CustomException(
                NetworkErrorType.UNKNOWN,
                R.string.err_msg_unknown_error_occured
            )
        }
    }
}

