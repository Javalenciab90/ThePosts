package com.javalenciab90.design_system.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
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
                    IconButton(
                        modifier = Modifier.size(14.dp),
                        onClick = {

                        }
                    ) {

                    }
                }
            )
        },
        expanded = false,
        onExpandedChange = {},
        content = {}
    )
}