package com.javalenciab90.data.datasource.remote

import android.util.Log
import com.javalenciab90.data.mapper.NetworkDataMapper
import com.javalenciab90.data.service.api.PostsApi
import com.javalenciab90.domain.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRemoteDataImpl @Inject constructor(
    private val postsApi: PostsApi,
    private val networkDataMapper: NetworkDataMapper
) : PostsRemoteData {

    override suspend fun getAllPosts(): Flow<List<Post>> = flow {
        try {
            val response = postsApi.getPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    val posts = networkDataMapper.map(it)
                    emit(posts)
                }
            } else {
                throw RuntimeException("Unexpected error API: ${response.message()}")
            }
        } catch (e: Exception) {
            throw RuntimeException("Unexpected error: ${e.message}")
        }
    }
}