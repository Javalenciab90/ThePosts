package com.javalenciab90.theposts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.javalenciab90.detail.navigation.buildDetailScreen
import com.javalenciab90.domain.routes.PostsRoutes
import com.javalenciab90.posts.navigation.buildPostsScreen
import com.javalenciab90.posts.navigation.buildSplashScreen

@Composable
fun NavigatorAppHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = PostsRoutes.SplashScreen
    ) {
        buildSplashScreen(navController)
        buildPostsScreen(navController)
        buildDetailScreen(navController)
    }
}