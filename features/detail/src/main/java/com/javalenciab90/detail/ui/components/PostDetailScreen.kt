package com.javalenciab90.detail.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.javalenciab90.design_system.R
import com.javalenciab90.detail.ui.PostDetailContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostDetailScreen(
    onHandleIntent: (PostDetailContract.Intent) -> Unit,
    onBack: () -> Unit,
    pageContent: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.wrapContentHeight(),
                title = { Text("Post Comments") },
                actions = {
                    IconButton(
                        onClick = {
                            onHandleIntent(PostDetailContract.Intent.ShowDialogComment)
                        }
                    )  {
                        Icon(
                            painter = painterResource(R.drawable.ic_add_comment),
                            contentDescription = null
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }
                    )  {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            pageContent(paddingValues)
        }
    )
}