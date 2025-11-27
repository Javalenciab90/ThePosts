package com.javalenciab90.detail.di

import com.javalenciab90.detail.domain.repository.CommentsRepository
import com.javalenciab90.detail.domain.repository.CommentsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsCommentsModule {

    @Binds
    abstract fun bindsCommentsRepository(impl: CommentsRepositoryImpl) : CommentsRepository
}