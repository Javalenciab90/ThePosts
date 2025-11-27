package com.javalenciab90.design_system.components.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.javalenciab90.design_system.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearSearch: () -> Unit
) {
    SearchBar(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
        inputField = {
            SearchBarDefaults.InputField(
                modifier = Modifier.fillMaxWidth(),
                query = query,
                onQueryChange = { onQueryChange(it) },
                expanded = false,
                onExpandedChange = {},
                onSearch = {},
                placeholder = {
                    Text(
                        text = "Buscar nombre ó ID"
                    )
                },
                trailingIcon = {
                    TrailingIconTextSearchInput(
                        showRemoveButton = query.isNotBlank(),
                        onCleanSearchTextPressed = onClearSearch
                    )
                }
            )
        },
        expanded = false,
        onExpandedChange = {},
        content = {}
    )
}


@Composable
fun TrailingIconTextSearchInput(
    showRemoveButton: Boolean,
    onCleanSearchTextPressed: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onCleanSearchTextPressed
        ) {
            if (showRemoveButton) {
                Icon(
                    modifier = Modifier.clickable {
                        onCleanSearchTextPressed()
                    },
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    tint = Color.Gray
                )
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}