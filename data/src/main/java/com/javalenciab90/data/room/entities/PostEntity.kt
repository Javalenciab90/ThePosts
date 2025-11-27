package com.javalenciab90.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.javalenciab90.domain.Post

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
