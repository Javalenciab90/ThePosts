package com.javalenciab90.data.room.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javalenciab90.data.room.entities.PostEntity
import kotlinx.coroutines.flow.Flow

interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPosts(postsEntity: List<PostEntity>)

    @Query("SELECT * FROM posts_table")
    fun getAllPosts() : Flow<List<PostEntity>>

    /**
     * The @Query get Entity when title or id fields contains the query.
     */
    @Query("SELECT * FROM posts_table WHERE title LIKE '%' || :query || '%' OR id = CAST(:query AS INTEGER) LIMIT 1")
    suspend fun getPost(query: String): PostEntity?
}