package com.javalenciab90.data.datasource.local

import com.javalenciab90.data.mapper.LocalDataMapper
import com.javalenciab90.data.room.database.PostsDao
import com.javalenciab90.domain.models.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostsLocalDataImpl @Inject constructor(
    private val postsDao: PostsDao,
    private val localDataMapper: LocalDataMapper
) : PostsLocalData {

    override fun insertAllPosts(posts: List<Post>) {
        postsDao.insertAllPosts(
            postsEntity = localDataMapper.map(posts)
        )
    }

    override fun getAllPosts(): List<Post> {
        return postsDao.getAllPosts().map { it.toModel() }
    }
}