package ar.com.wolox.android.example.model

data class User(
    val id: Int,
    val email: String,
    val provider: String,
    val uid: String,
    val allow_password_change: Boolean,
    val name: String,
    val nickname: String,
    val image: String?
)