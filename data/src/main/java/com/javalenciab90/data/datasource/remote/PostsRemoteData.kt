package com.javalenciab90.data.datasource.remote

import com.javalenciab90.domain.Post
import kotlinx.coroutines.flow.Flow

interface PostsRemoteData {
    suspend fun getAllPosts() : Flow<List<Post>>
}