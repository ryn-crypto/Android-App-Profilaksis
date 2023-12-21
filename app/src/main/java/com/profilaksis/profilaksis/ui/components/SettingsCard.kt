package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profilaksis.profilaksis.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    icon: Int,
    title: String,
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier
                    .weight(1f),
                painter = painterResource(id = icon),
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "History card"
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(2f)
            )
            Icon(
                modifier = Modifier
                    .weight(1f),
                imageVector = Icons.Filled.KeyboardArrowRight,
                tint = MaterialTheme.colorScheme.onSurface,
                contentDescription = "History card"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsCardPreview() {
    SettingsCard(onClick = { /*TODO*/ }, icon = R.drawable.logout, title = "Logout")
}