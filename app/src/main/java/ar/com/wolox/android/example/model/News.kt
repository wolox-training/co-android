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
) : Serializable {
    fun update(new: NewDetailResponse) {
        commenter = new.commenter
        comment = new.comment
        date = new.date
        avatar = new.avatar
        likes = new.likes.toMutableList()
    }
}