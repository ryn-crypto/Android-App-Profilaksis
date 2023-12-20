package com.profilaksis.profilaksis.ui.screen.consultation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import java.lang.reflect.Modifier

@Composable
fun Consultation(
    clickBack: () -> Unit, clickReset: () -> Unit,
) {
    BackHandler(onBack = {
        clickBack()
        clickReset()
    })

    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Under Maintenance",
            style = MaterialTheme.typography.titleMedium
        )
    }
}