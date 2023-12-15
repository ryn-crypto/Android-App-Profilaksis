package com.profilaksis.profilaksis.ui.screen.heart

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profilaksis.profilaksis.ui.components.DiabetesRadioButton
import com.profilaksis.profilaksis.ui.components.DiabetesStatus

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeartScreen(clickBack: () -> Unit) {
    var selectedStatus by remember { mutableStateOf(DiabetesStatus.No) }

    BackHandler(onBack = clickBack)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.padding(5.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = clickBack) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = "Back",
                                modifier = Modifier.padding(end = 10.dp)
                            )
                        }
                        Text(text = "Heart Check")
                    }
                },
                modifier = Modifier.background(MaterialTheme.colorScheme.primary)
            )
        },
        content = {
            Column(
                modifier = Modifier.padding(top = 60.dp)
            ) {

                androidx.compose.material.Text(
                    text = "Riwayat Diabetes", modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
                )
                DiabetesRadioButton(
                    selectedStatus = selectedStatus,
                    onStatusSelected = { status ->
                        selectedStatus = status
                    },
                    modifier = Modifier.padding(10.dp))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HeartPreview() {
    HeartScreen(
        clickBack = {}
    )
}