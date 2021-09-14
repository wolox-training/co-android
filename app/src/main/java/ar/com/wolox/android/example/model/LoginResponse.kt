package ar.com.wolox.android.example.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val data: DataUserResponse,
    val success: Boolean,
    val errors: List<String>
)

data class DataUserResponse(
    @SerializedName("id") val idReferencia: Int,
    val email: String,
    val password: String,
    @SerializedName("provider") val proveedor: String,
    val uid: String,
    val allowPasswordChange: Boolean,
    @SerializedName("name") val nombre: String,
    @SerializedName("nickname") val apodo: String,
    @SerializedName("image") val imagen: String? = null
)