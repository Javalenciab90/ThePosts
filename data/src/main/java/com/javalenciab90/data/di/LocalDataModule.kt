package com.javalenciab90.data.di

import android.app.Application
import androidx.room.Room
import com.javalenciab90.data.datasource.local.PostsLocalDataImpl
import com.javalenciab90.data.mapper.LocalDataMapper
import com.javalenciab90.data.room.database.PostsDao
import com.javalenciab90.data.room.database.PostsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val NAME_DATABASE = "PostsAppDatabase"

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {

    @Provides
    @Singleton
    fun providesPostsDatabase(app: Application): PostsDatabase {
        return Room.databaseBuilder(
            app, PostsDatabase::class.java,
            NAME_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun providePostsDao(postsDatabase: PostsDatabase): PostsDao {
        return postsDatabase.postsDao()
    }

    @Provides
    fun providePostsLocalData(
        postsDao: PostsDao,
        localDataMapper: LocalDataMapper
    ) : PostsLocalDataImpl {
        return PostsLocalDataImpl(postsDao, localDataMapper)
    }
}
