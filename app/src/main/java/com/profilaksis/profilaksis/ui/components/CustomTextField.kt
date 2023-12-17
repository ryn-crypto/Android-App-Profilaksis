package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profilaksis.profilaksis.utils.RedText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    name: String,
    label: String,
    rightLabel: String = "*",
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    TextField(
        singleLine = true,
        value = name,
        onValueChange = onValueChange,
        label = {
            Text(text = label, color = MaterialTheme.colorScheme.onSurface)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
        ),
        trailingIcon = {
            RedText(text = rightLabel)
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CustomTextFieldPreview() {
    CustomTextField(
        name = "",
        label = "Name",
        onValueChange = {},
        modifier = Modifier.padding(10.dp)
    )
}
