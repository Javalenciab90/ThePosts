package com.javalenciab90.data.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javalenciab90.data.room.entities.PostEntity

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(postsEntity: List<PostEntity>)

    @Query("SELECT * FROM posts_table")
    fun getAllPosts() : List<PostEntity>

    /**
     * The @Query get Entity when title or id fields contains the query.
     */
    @Query("SELECT * FROM posts_table WHERE title LIKE '%' || :query || '%' OR id = CAST(:query AS INTEGER) LIMIT 1")
    fun getPost(query: String): PostEntity?
}