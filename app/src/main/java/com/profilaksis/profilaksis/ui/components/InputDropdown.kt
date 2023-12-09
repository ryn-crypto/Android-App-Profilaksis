package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownInput(
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    placeholder: String
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf(placeholder) }

    Column {
        Text("Select an option:")

        Box(modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })) {
            TextField(
                value = textFieldValue,
                onValueChange = { },
                readOnly = true,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, "Toggle Dropdown")
                }
            )
        }

        if (expanded) {
            DropdownOptions(options, onOptionSelected = { option ->
                onOptionSelected(option)
                textFieldValue = option
                expanded = false
            })
        }
    }
}

@Composable
private fun DropdownOptions(
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    DropdownMenu(
        expanded = true,
        onDismissRequest = { },
        modifier = Modifier.fillMaxWidth()
    ) {
        options.forEach { option ->
            DropdownMenuItem(onClick = {
                onOptionSelected(option)
            }) {
                Text(option)
            }
        }
    }
}

@Composable
fun StatefulDropdownInput() {
    var selectedOption by remember { mutableStateOf("") }
    var placeholderText by remember { mutableStateOf("Select an option...") }

    val options = listOf("Option 1", "Option 2", "Option 3")

    DropdownInput(
        options = options,
        selectedOption = selectedOption,
        onOptionSelected = { option ->
            selectedOption = option
            placeholderText = option
        },
        placeholder = placeholderText
    )
}

@Preview(showBackground = true)
@Composable
fun StatelessDropdownPreview() {
    StatefulDropdownInput()
}