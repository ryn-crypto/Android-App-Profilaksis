package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hintText: String = "Search",
    searchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchPerformed: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    Row(
        modifier = modifier.background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(
                topStart = MaterialTheme.shapes.medium.topStart,
                topEnd = MaterialTheme.shapes.medium.topEnd,
                bottomStart = MaterialTheme.shapes.medium.bottomStart,
                bottomEnd = MaterialTheme.shapes.medium.bottomEnd
            )
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchText,
            onValueChange = onSearchTextChanged,
            modifier = Modifier.weight(1f),
            placeholder = { Text(text = hintText) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchPerformed()
                    focusManager.clearFocus()
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                cursorColor = MaterialTheme.colorScheme.secondary,
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Gray
            )
        )
        Icon(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(8.dp),
            tint = MaterialTheme.colorScheme.secondary,
            imageVector = Icons.Filled.Search,
            contentDescription = "Search"
        )
    }
}

// Preview
@Preview
@Composable
fun SearchBarPreview() {
    var searchText by remember { mutableStateOf("") }
    SearchBar(
        hintText = "Search",
        searchText = searchText,
        onSearchTextChanged = { newText ->
            searchText = newText
        },
        onSearchPerformed = {}
    )
}

