package com.interview.data.requests

data class LoginUserRequestDto(
    var email: String = "",
    var username: String = "",
    var password: String = ""
)