package com.javalenciab90.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javalenciab90.domain.models.PostComment
import com.javalenciab90.domain.models.Post

@Entity(tableName = "posts_table")
data class PostEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) {
    fun toModel() : Post =
        Post(
            id = id,
            userId = userId,
            title = title,
            body = body
        )
}

@Entity(tableName = "comments_table")
data class PostCommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val postId: Int,
    val comment: String
) {
    fun toModel() : PostComment =
        PostComment(
            postId = postId,
            comment = comment
        )
}