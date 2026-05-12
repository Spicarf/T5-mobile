package com.core.pasien.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access_token")
    val accessToken: String?,

    @SerializedName("token")
    val token: String?,

    @SerializedName("message")
    val message: String?,

    @SerializedName("user")
    val user: UserData?,

    // Kalau dibungkus dalam key "data"
    @SerializedName("data")
    val data: LoginData?
)

data class LoginData(
    @SerializedName("access_token")
    val accessToken: String?,

    @SerializedName("token")
    val token: String?,

    @SerializedName("user")
    val user: UserData?
)

data class UserData(
    val id: Int?,
    val name: String?,
    val email: String?
)

data class User(
    val id: Int?,
    val name: String?,
    val email: String?
)
