package com.interview.data.remote

data class BaseApiResponse<T>(
    var data: T?,
    val code: Int,
    val message: String?,
    var success: Boolean
)