package com.javalenciab90.detail.domain.repository

import com.javalenciab90.data.datasource.local.PostsLocalData
import com.javalenciab90.domain.models.PostComment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val localData: PostsLocalData
) : CommentsRepository {

    override fun insertNewComment(postComment: PostComment) {
        localData.insertNewComment(postComment)
    }

    override suspend fun getAllComments(postId: Int): Flow<List<PostComment>> = flow {
        emit(localData.getAllComments(postId = postId))
    }
}