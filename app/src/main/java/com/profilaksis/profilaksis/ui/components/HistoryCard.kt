package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCard(
    percentage: Float,
    date: String,
    describe: String,
) {
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Text(
                text = date,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 20.dp, start = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                PercentageCircle(
                    percentage = percentage,
                    withPercentage = false,
                    sizeText = 10,
                    canvasSize = 90,
                    modifier = Modifier
                        .height(70.dp)
                        .width(70.dp)
                        .weight(2f)
                )
                Text(
                    text = describe,
                    maxLines = 4,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(4f)
                )
                Icon(
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                        .weight(1f),
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = "History card"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryCardPreview() {
    HistoryCard(
        percentage = 70f,
        date = "12 December 2021",
        describe = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae ni"
    )
}