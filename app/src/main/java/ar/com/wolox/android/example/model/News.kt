package ar.com.wolox.android.example.model

data class News(
    val id: Int,
    val commenter: String,
    val comment: String,
    val date: String,
    val avatar: String,
    val likes: List<Int>,
    val createdAt: String,
    val updatedAt: String
)