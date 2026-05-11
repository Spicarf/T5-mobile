package com.core.pasien.model

import com.google.gson.annotations.SerializedName

data class Pasien(
    val id: Int?,
    val name: String?,
    val no_rm: String?,
    val tanggal_lahir: String?,
    val jenis_kelamin: String?,
    val alamat: String?,
    val no_telepon: String?,
    val created_at: String?,
    val updated_at: String?
)
