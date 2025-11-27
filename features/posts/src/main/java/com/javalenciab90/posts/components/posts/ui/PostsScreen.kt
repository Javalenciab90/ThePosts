package com.javalenciab90.posts.components.posts.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.javalenciab90.design_system.components.search.PostsSearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    pageContent: @Composable (PaddingValues) -> Unit,
    onPostClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.wrapContentHeight(),
                title = { Text("") },
                actions = {
                    PostsSearchBar(
                        query = "",
                        onQueryChange = {}
                    )
                }
            )
        },
        content = { paddingValues ->
            pageContent(paddingValues)
        }
    )
}