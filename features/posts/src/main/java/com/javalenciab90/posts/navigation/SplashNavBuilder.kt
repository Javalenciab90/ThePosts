package com.javalenciab90.posts.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.javalenciab90.domain.routes.PostsRoutes
import com.javalenciab90.posts.components.splash.SplashContent
import com.javalenciab90.posts.components.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val MIN_SPLASH_DURATION_MS = 3000L

fun NavGraphBuilder.buildSplashScreen(
    navController: NavController
) {
    composable<PostsRoutes.SplashScreen> {

        LaunchedEffect(Unit) {
            val splashTimerJob = launch {
                delay(MIN_SPLASH_DURATION_MS)
                navController.goToPostsScreen()
            }
            splashTimerJob.join()
        }

        SplashScreen {
            SplashContent()
        }
    }
}

fun NavController.goToPostsScreen() {
    navigate(PostsRoutes.PostsScreen) {
        popUpTo(PostsRoutes.SplashScreen) {
            inclusive = true
        }
    }
}