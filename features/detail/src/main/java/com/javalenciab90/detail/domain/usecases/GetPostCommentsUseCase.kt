package com.javalenciab90.detail.domain.usecases

import com.javalenciab90.detail.domain.repository.CommentsRepository
import com.javalenciab90.domain.models.PostComment
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
){
    suspend operator fun invoke(postId: Int) : Flow<List<PostComment>> {
        return commentsRepository.getAllComments(postId)
    }
}