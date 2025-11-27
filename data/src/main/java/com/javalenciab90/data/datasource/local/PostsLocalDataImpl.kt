package com.javalenciab90.data.datasource.local

import com.javalenciab90.data.mapper.LocalCommentsDataMapper
import com.javalenciab90.data.mapper.LocalPostDataMapper
import com.javalenciab90.data.room.database.PostsDao
import com.javalenciab90.domain.models.Post
import com.javalenciab90.domain.models.PostComment
import javax.inject.Inject

class PostsLocalDataImpl @Inject constructor(
    private val postsDao: PostsDao,
    private val localPostDataMapper: LocalPostDataMapper,
    private val localCommentsDataMapper: LocalCommentsDataMapper
) : PostsLocalData {

    override fun insertAllPosts(posts: List<Post>) {
        postsDao.insertAllPosts(
            postsEntity = localPostDataMapper.map(posts)
        )
    }

    override fun getAllPosts(): List<Post> {
        return postsDao.getAllPosts().map { it.toModel() }
    }

    override fun insertNewComment(postComment: PostComment) {
        postsDao.insertPostComment(
            postCommentEntity = localCommentsDataMapper.map(postComment)
        )
    }

    override fun getAllComments(postId: Int): List<PostComment> {
        return postsDao.getAllPostComments(postId).map { it.toModel() }
    }
}