package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.profilaksis.profilaksis.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    url: String,
    title: String,
    author: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = modifier.width(300.dp).padding(5.dp),
        onClick = onClick
    ) {
//        Image(url, modifier = modifier.padding(8.dp))
        Column {
            Text(title, Modifier.padding(8.dp))
            Row {
                Button(
                    onClick = {},
                    Modifier.padding(8.dp),
                ) {
                    Text(text = "Go to article")
                }
                Column {
                    Text(
                        "Article By :",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(8.dp),

                        )
                    Spacer(Modifier.weight(1f))
                    Text(
                        author,
                        Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }
        }

    }
}

@Composable
fun Image(url: String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
//            placeholder = painterResource(R.drawable.placeholder), // skeleton
        contentDescription = stringResource(R.string.description_image),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true)
@Composable
fun ArticleCardPreview() {
    val url =
        "https://img.freepik.com/free-photo/painting-mountain-lake-with-mountain-background_188544-9126.jpg"
    ArticleCard(url, "Title", "Lorem Ipsum Dolor Sit Amet", modifier = Modifier)
}