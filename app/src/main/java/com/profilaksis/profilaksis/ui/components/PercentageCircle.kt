package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PercentageCircle(percentage: Float) {
    val color = when {
        percentage >= 70 -> Color.Green
        percentage >= 50 -> Color.Yellow
        else -> Color.Red
    }
    Canvas(modifier = Modifier.size(200.dp)) {
        val strokeWidth = 20f
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.width / 2 - strokeWidth

        drawArc(
            color = color,
            startAngle = -90f,
            sweepAngle = 360f * (percentage / 100f),
            useCenter = false,
            style = Stroke(width = strokeWidth),
            topLeft = Offset(center.x - radius, center.y - radius),
            size = Size(radius * 2, radius * 2)
        )
    }
}


@Composable
fun PercentageCircleWrapper(percentage: Float) {
    var currentPercentage by remember { mutableStateOf(percentage) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PercentageCircle(percentage = currentPercentage)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (currentPercentage < 100) {
                    currentPercentage += 10
                } else {
                    currentPercentage = 0F
                }
            }) {
            Text(text = "Tambah")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PercentageCirclePreview() {
    PercentageCircleWrapper(percentage = 0F)
}

