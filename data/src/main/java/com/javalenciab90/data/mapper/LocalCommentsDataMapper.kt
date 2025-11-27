package com.javalenciab90.data.mapper

import com.javalenciab90.data.room.entities.PostCommentEntity
import com.javalenciab90.domain.mapper.DataMapper
import com.javalenciab90.domain.models.PostComment
import javax.inject.Inject

class LocalCommentsDataMapper @Inject constructor(): DataMapper<PostComment, PostCommentEntity>{

    override fun map(input: PostComment): PostCommentEntity {
        return PostCommentEntity(
                postId = input.postId,
                comment = input.comment
            )
    }
}