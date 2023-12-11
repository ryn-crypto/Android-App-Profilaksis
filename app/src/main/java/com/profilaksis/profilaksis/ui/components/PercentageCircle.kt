package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PercentageCircle(
    percentage: Float,
    withPercentage: Boolean,
    sizeText: Int = 24,
    canvasSize: Int = 150,
    modifier: Modifier = Modifier
) {

    val color = when {
        percentage >= 90 -> Color.Red
        percentage >= 60 -> Color.Yellow
        else -> Color.Green
    }
    val status = when {
        percentage >= 90 -> "Danger"
        percentage >= 60 -> "UnHealthy"
        else -> "Healthy"
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(canvasSize.dp)) {
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
        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (withPercentage) {
                Text(

                    text = "${percentage.toInt()}%",
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Text(
                text = status,
                style = MaterialTheme.typography.bodySmall,
                fontSize = sizeText.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PercentageCirclePreview() {
    PercentageCircle(percentage = 90F, withPercentage = true)
}

