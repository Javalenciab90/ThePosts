package com.javalenciab90.data.datasource.local

import com.javalenciab90.domain.models.Post

interface PostsLocalData {

    fun insertAllPosts(posts: List<Post>)

    fun getAllPosts() : List<Post>


}