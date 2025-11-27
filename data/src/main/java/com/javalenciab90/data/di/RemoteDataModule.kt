package com.javalenciab90.data.di

import com.javalenciab90.data.datasource.remote.PostsRemoteData
import com.javalenciab90.data.datasource.remote.PostsRemoteDataImpl
import com.javalenciab90.data.mapper.NetworkDataMapper
import com.javalenciab90.data.service.api.PostsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): PostsApi {
        return retrofit.create(PostsApi::class.java)
    }

    @Provides
    fun bindsPostsRemoteData(
        service: PostsApi,
        networkMapper: NetworkDataMapper
    ) : PostsRemoteData {
        return PostsRemoteDataImpl(
            service, networkMapper
        )
    }
}