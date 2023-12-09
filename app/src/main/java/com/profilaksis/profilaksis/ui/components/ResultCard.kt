package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResultCard(percentage: Float,date: String, userName: String, description: String) {
    ElevatedCard(
        modifier = Modifier
            .width(300.dp)
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(20.dp),
        )
        PercentageCircle(
            percentage,
            Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
        )
        Text(
            text = userName,
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelSmall,
        )
        Text(
            text = description,
            modifier = Modifier
                .padding(16.dp),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Preview(device = "id:pixel_5", showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun CardsPreview() {
    Column(
        modifier = Modifier
            .padding(20.dp)
    )
    {
        ResultCard(
            30f,
            "12 December 2021",
            "Nama Pasien",
            "lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet"
        )
    }
}
