package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profilaksis.profilaksis.data.model.RadioButtonInfo
import com.profilaksis.profilaksis.utils.Status

@Composable
fun <T : Enum<T>> CustomRadioButton(
    radioButtons: List<RadioButtonInfo<T>>,
    modifier: Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        for (radioButton in radioButtons) {
            RadioButtonItem(radioButton)
        }
    }
}

@Composable
fun <T : Enum<T>> RadioButtonItem(radioButtonInfo: RadioButtonInfo<T>) {
    val (status, name, selectedStatus, onStatusSelected) = radioButtonInfo

    Row(
        modifier = Modifier.padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = status.ordinal == selectedStatus.ordinal,
            onClick = { onStatusSelected(status) }
        )
        Text(text = name)
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_4)
@Composable
fun DiabetesRadioButtonPreview() {
    var diabetesStatus by remember { mutableStateOf(Status.TreeLevel.Low) }

    val radioButtons = listOf(
        RadioButtonInfo(Status.TreeLevel.Low, "No", diabetesStatus) { status ->
            diabetesStatus = status
        },
        RadioButtonInfo(Status.TreeLevel.Mid, "Prediabetes", diabetesStatus) { status ->
            diabetesStatus = status
        },
        RadioButtonInfo(Status.TreeLevel.Height, "Yes", diabetesStatus) { status ->
            diabetesStatus = status
        }
    )


    CustomRadioButton(radioButtons, Modifier.wrapContentWidth())
}