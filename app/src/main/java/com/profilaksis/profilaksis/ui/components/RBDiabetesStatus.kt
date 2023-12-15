package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

enum class DiabetesStatus(val label: String) {
    No("No"),
    Prediabetes("Prediabetes"),
    Yes("Ya")
}

@Composable
fun DiabetesRadioButton(
    selectedStatus: DiabetesStatus,
    onStatusSelected: (DiabetesStatus) -> Unit,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        RadioButtonItem(
            DiabetesStatus.No,
            selectedStatus,
            onStatusSelected
        )
        RadioButtonItem(
            DiabetesStatus.Prediabetes,
            selectedStatus,
            onStatusSelected
        )
        RadioButtonItem(
            DiabetesStatus.Yes,
            selectedStatus,
            onStatusSelected
        )
    }
}

@Composable
fun RadioButtonItem(
    status: DiabetesStatus,
    selectedStatus: DiabetesStatus,
    onStatusSelected: (DiabetesStatus) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = status == selectedStatus,
            onClick = { onStatusSelected(status) }
        )
        Text(text = status.label)
    }
}

@Preview(showBackground = true)
@Composable
fun DiabetesRadioButtonPreview() {
    var selectedStatus by remember { mutableStateOf(DiabetesStatus.No) }

    DiabetesRadioButton(
        selectedStatus = selectedStatus,
        onStatusSelected = { status ->
            selectedStatus = status
        },
        modifier = Modifier.wrapContentWidth()
    )
}