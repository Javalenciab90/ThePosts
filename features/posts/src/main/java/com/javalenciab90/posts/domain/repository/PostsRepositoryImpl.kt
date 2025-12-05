package com.javalenciab90.posts.domain.repository

import com.javalenciab90.data.datasource.local.PostsLocalData
import com.javalenciab90.data.datasource.remote.PostsRemoteData
import com.javalenciab90.domain.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsLocalData: PostsLocalData,
    private val postsRemoteData: PostsRemoteData
) : PostsRepository {

    override fun getAllPosts(): Flow<List<Post>> = flow {
        postsLocalData.getAllPosts().collect { localResult ->
            if (localResult.isEmpty()) {
                postsRemoteData.getAllPosts().collect { remoteResult ->
                    postsLocalData.insertAllPosts(remoteResult)
                    emit(remoteResult)
                }
            } else {
                emit(localResult)
            }
        }
    }
}