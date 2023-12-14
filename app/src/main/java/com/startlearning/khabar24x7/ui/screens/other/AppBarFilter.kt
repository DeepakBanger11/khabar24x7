package com.startlearning.khabar24x7.ui.screens.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarFilter(
    onSortClick: () -> Unit,
    onSearchTextChanged: (String) -> Unit,
    searchQuery: String
) {

    Row(
        modifier = Modifier
            .height(75.dp)
            .background(MaterialTheme.colorScheme.surfaceContainer),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search field
        TextField(
            value = searchQuery,
            onValueChange = { onSearchTextChanged(it) },
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            placeholder = {
                Text(text = "Search by Title, Author or Description")
            },
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        // Sort icon
        IconButton(
            onClick = { onSortClick() },
            modifier = Modifier.padding(end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.SortByAlpha, contentDescription = "Sort")
        }
    }
}