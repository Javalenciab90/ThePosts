package com.javalenciab90.posts.domain.repository

import com.javalenciab90.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    fun getAllPosts() : Flow<List<Post>>
}