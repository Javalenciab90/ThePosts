package com.javalenciab90.platform.di

import com.javalenciab90.platform.base.CoroutineContextProvider
import com.javalenciab90.platform.base.DefaultCoroutineContextProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineModule {

    @Binds
    abstract fun bindCoroutineContextProvider(
        defaultCoroutineContextProvider: DefaultCoroutineContextProvider
    ): CoroutineContextProvider
}