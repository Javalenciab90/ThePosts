package com.javalenciab90.data.datasource.local

import com.javalenciab90.data.mapper.LocalDataMapper
import com.javalenciab90.data.room.database.PostsDao
import com.javalenciab90.domain.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PostsLocalDataImpl @Inject constructor(
    private val postsDao: PostsDao,
    private val localDataMapper: LocalDataMapper
) : PostsLocalData {

    override suspend fun insertAllPosts(posts: List<Post>) {
        postsDao.insertAllPosts(
            postsEntity = localDataMapper.map(posts)
        )
    }

    override suspend fun getPost(query: String): Post? {
        return postsDao.getPost(query)?.toModel()
    }

    override fun getAllPosts(): Flow<List<Post>> {
        return postsDao.getAllPosts().map { entityList -> entityList.map { it.toModel() } }
    }

}