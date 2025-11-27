package com.javalenciab90.detail.domain.usecases

import com.javalenciab90.detail.domain.repository.CommentsRepository
import com.javalenciab90.domain.models.PostComment
import javax.inject.Inject

class AddNewCommentUseCase @Inject constructor(
    private val commentsRepository: CommentsRepository
) {
    operator fun invoke(postComment: PostComment) {
        commentsRepository.insertNewComment(postComment)
    }
}
