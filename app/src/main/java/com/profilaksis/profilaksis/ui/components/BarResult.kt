package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BarResult(title: String, percent: Float, onClick: () -> Unit) {
    val health = when {
        percent > 90 -> "Danger"
        percent > 60 -> "UnHealthy"
        else -> "Healthy"
    }
    val gradientBrush = when {
        percent < 35 -> Brush.linearGradient(
            colors = listOf(
                Color.Green,
                Color.Green
            )
        )

        percent < 75 -> Brush.linearGradient(
            colors = listOf(
                Color.Yellow,
                Color.Green
            )
        )

        else -> Brush.linearGradient(
            colors = listOf(
                Color.Green,
                Color.Yellow,
                Color.Red
            )
        )
    }
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            val screenWidth = LocalConfiguration.current.screenWidthDp.dp
            (screenWidth * percent / 100)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = percent / 100)
                        .height(16.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(brush = gradientBrush)
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "${percent.toInt()}%", style = MaterialTheme.typography.bodyMedium)
            Text(text = health, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    modifier = Modifier
                        .wrapContentSize(),
                    onClick = { onClick }) {
                    Text(
                        text = "test again",
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BarResultPreview() {
    BarResult(
        title = "Health Update",
        percent = 100f,
        onClick = {}
    )
}