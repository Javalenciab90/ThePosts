package com.javalenciab90.posts.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.javalenciab90.domain.routes.PostsRoutes
import com.javalenciab90.posts.components.posts.PostsScreen

fun NavGraphBuilder.buildPostsScreen(
    navController: NavController
) {
    composable<PostsRoutes.PostsScreen> {

        PostsScreen(
            pageContent = { paddingValues ->
                Text(
                    modifier = Modifier.padding(paddingValues),
                    text = "TODO: Post list implementation"
                )
            },
            onPostClick = {}
        )
    }
}