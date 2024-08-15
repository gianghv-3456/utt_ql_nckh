package com.gianghv.android.network.model.login

import com.gianghv.android.network.model.UserResponse
import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "accessToken")
    val accessToken: String? = null,
    @Json(name = "message")
    val message: String? = null,
    @Json(name = "user")
    val user: UserResponse? = null
)
