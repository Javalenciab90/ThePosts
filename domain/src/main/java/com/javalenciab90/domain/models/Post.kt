package com.javalenciab90.domain.models

data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)

data class PostComment(
    val id: Int,
    val postId: Int,
    val comment: String
)