package com.example.cekpasien.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String?,
    val token: String?,
    val user: User?
)

data class User(
    val id: Int?,
    val name: String?,
    val email: String?
)
