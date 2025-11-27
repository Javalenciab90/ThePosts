package com.javalenciab90.data.datasource.local

import com.javalenciab90.domain.Post
import kotlinx.coroutines.flow.Flow

interface PostsLocalData {

    suspend fun insertAllPosts(posts: List<Post>)

    suspend fun getPost(query: String) : Post?

    fun getAllPosts() : Flow<List<Post>>
}