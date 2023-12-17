package com.profilaksis.profilaksis.ui.screen.consultation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import java.lang.reflect.Modifier

@Composable
fun Consultation(clickBack: () -> Unit, clickReset: () -> Unit) {
    BackHandler(onBack = {
        clickBack()
        clickReset()
    })
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Consultation")
    }
}