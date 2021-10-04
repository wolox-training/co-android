package ar.com.wolox.android.example.model

data class NewDetailResponse(
    val commenter: String,
    val comment: String,
    val date: String,
    val avatar: String,
    val likes: List<Int>
)
