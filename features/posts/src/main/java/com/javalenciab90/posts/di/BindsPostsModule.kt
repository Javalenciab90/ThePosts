package com.javalenciab90.posts.di

import com.javalenciab90.posts.domain.repository.PostsRepository
import com.javalenciab90.posts.domain.repository.PostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsPostsModule {

    @Binds
    abstract fun bindsPostsRepository(postsRepositoryImpl: PostsRepositoryImpl) : PostsRepository
}