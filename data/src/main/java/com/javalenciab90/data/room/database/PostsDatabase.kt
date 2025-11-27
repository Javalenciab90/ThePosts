package com.javalenciab90.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javalenciab90.data.room.entities.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PostsDatabase : RoomDatabase() {
    abstract fun postsDao() : PostsDao
}