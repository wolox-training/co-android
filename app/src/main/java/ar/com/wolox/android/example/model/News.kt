package ar.com.wolox.android.example.model

import java.io.Serializable

data class News(
    val id: Int,
    var commenter: String,
    var comment: String,
    var date: String,
    var avatar: String,
    var likes: MutableList<Int>,
    val createdAt: String,
    val updatedAt: String
) : Serializable