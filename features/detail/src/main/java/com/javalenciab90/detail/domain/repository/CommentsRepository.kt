package com.javalenciab90.detail.domain.repository

import com.javalenciab90.domain.models.PostComment
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {
    fun insertNewComment(postComment: PostComment)
    suspend fun getAllComments(postId: Int) : Flow<List<PostComment>>
}