package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ResultCard(
    modifier: Modifier = Modifier,
    percentage: Float,
    date: String,
    userName: String?,
    description: String?,
    onclick: () -> Unit = {},
    back: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 10.dp),
        )
        PercentageCircle(
            percentage,
            true,
            24,
            150,
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 5.dp)
        )
        if (userName != null && description != null) {
            Text(
                text = userName,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = description,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.Absolute.Right,
            verticalAlignment = Alignment.Top
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                shape = MaterialTheme.shapes.medium,
                contentPadding = PaddingValues(15.dp, 2.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                onClick = back
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier
                            .width(15.dp),
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Icon to consult a doctor",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        text = "Test Again"
                    )
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                onClick = onclick,
                modifier = Modifier
                    .wrapContentSize()
                    .height(45.dp),
                shape = MaterialTheme.shapes.large,
                contentPadding = PaddingValues(15.dp, 5.dp),
            ) {
                Row(
                    modifier = Modifier
                        .size(110.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(3f),
                        text = "Consultation",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Icon(
                        modifier = Modifier
                            .weight(1f),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "Icon to consult a doctor"
                    )
                }
            }
        }

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
            modifier = Modifier
                .padding(5.dp),
            90f,
            "12 December 2021",
            "Nama Pasien",
            "lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet lorem ipsum dolor sit amet"
        )

        ResultCard(
            modifier = Modifier
                .padding(5.dp),
            37f,
            "12 December 2021",
            null,
            null
        )
    }
}
