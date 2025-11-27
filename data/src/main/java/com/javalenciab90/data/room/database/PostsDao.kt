package com.javalenciab90.data.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.javalenciab90.data.room.entities.PostCommentEntity
import com.javalenciab90.data.room.entities.PostEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPosts(postsEntity: List<PostEntity>)

    @Query("SELECT * FROM posts_table")
    fun getAllPosts() : List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPostComment(postCommentEntity: PostCommentEntity)

    @Query("SELECT * FROM comments_table WHERE postId = :postId")
    fun getAllPostComments(postId: Int) : List<PostCommentEntity>
}