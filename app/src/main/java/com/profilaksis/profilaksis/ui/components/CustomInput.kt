package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.profilaksis.profilaksis.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInput(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leftIcon: ImageVector,
    rightIcon: ImageVector ?= null,
    rightIconFalse: ImageVector ?= null,
    isRightIconEnabled: Boolean,
    onRightIconClick: () -> Unit = {},
    visibleIcon: Boolean,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        singleLine = true,
        onValueChange = {
            text = it
            onValueChange(it)
        },
        modifier = modifier,
        label = { Text(placeholder, color = Color.Gray) },
        leadingIcon = { Icon(imageVector = leftIcon, contentDescription = "Left Icon") },
        trailingIcon = {
            if (isRightIconEnabled) {
                IconButton(
                    onClick = { onRightIconClick() },
                ) {
                    (if (visibleIcon) rightIcon else rightIconFalse)?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "Right Icon"
                        )
                    }
                }
            }
        },
        visualTransformation = if (visibleIcon) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {}
        )
    )
}

@Composable
fun CustomInputFieldWrapper() {
    var visible by remember { mutableStateOf(false) }
    val placeholder = "Password"

    CustomInput(
        placeholder = placeholder,
        leftIcon = Icons.Default.Lock,
        rightIcon = ImageVector.vectorResource(R.drawable.visible),
        rightIconFalse = ImageVector.vectorResource(R.drawable.invisible),
        isRightIconEnabled = true,
        onRightIconClick = { visible = !visible },
        visibleIcon = visible,
        onValueChange = {},
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun CustomInputPreview() {
    CustomInputFieldWrapper()
}
