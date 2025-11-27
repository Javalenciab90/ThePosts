package com.javalenciab90.data.datasource.local

import com.javalenciab90.domain.models.Post
import com.javalenciab90.domain.models.PostComment

interface PostsLocalData {

    fun insertAllPosts(posts: List<Post>)

    fun getAllPosts() : List<Post>

    fun insertNewComment(postComment: PostComment)

    fun getAllComments(postId: Int) : List<PostComment>
}