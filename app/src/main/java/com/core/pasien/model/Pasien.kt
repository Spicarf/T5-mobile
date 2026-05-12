package com.core.pasien.model

import com.google.gson.annotations.SerializedName

data class Pasien(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("nama")
    val nama: String?,

    @SerializedName("tanggal_lahir")
    val tanggalLahir: String?,

    @SerializedName("jenis_kelamin")
    val jenisKelamin: String?,

    @SerializedName("alamat")
    val alamat: String?,

    @SerializedName("no_telepon")
    val noTelepon: String?,

    @SerializedName("created_at")
    val createdAt: String?
)