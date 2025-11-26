package com.javalenciab90.detail.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.javalenciab90.domain.routes.PostsRoutes

fun NavGraphBuilder.buildDetailScreen(
    navController: NavController
) {
    composable<PostsRoutes.DetailScreen> {
        Text("Detail Screen")
    }
}