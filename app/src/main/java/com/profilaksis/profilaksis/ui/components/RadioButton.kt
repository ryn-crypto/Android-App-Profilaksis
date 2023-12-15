package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonExample() {
    var selectedOption by remember { mutableStateOf("No") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption == "No",
                onClick = { selectedOption = "No" },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("No")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedOption == "Yes",
                onClick = { selectedOption = "Yes" },
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Yes")
        }
    }
}

