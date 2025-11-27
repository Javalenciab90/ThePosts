package com.javalenciab90.domain.routes

import kotlinx.serialization.Serializable

@Serializable
sealed interface PostsRoutes {

    @Serializable
    data object SplashScreen: PostsRoutes

    @Serializable
    data object PostsScreen: PostsRoutes

    @Serializable
    data class DetailScreen(val postId: Int): PostsRoutes
}