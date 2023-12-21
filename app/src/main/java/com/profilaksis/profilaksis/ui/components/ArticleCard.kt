package com.profilaksis.profilaksis.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.profilaksis.profilaksis.R

@Composable
fun ArticleCard(
    url: String,
    title: String,
    author: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Image(
            url,
            modifier = modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(3.dp)
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.padding(start = 15.dp, bottom = 2.dp),
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    modifier = modifier
                        .padding(start = 10.dp, bottom = 2.dp)
                        .align(Alignment.CenterVertically),
                    onClick = onClick,
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    contentPadding = PaddingValues(vertical = 2.dp, horizontal = 10.dp),
                ) {
                    Text(
                        text = "Go to article",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        "Article By:",
                        style = MaterialTheme.typography.bodySmall,

                        )
                    Text(
                        author,
                        Modifier.padding(3.dp),
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
        modifier = modifier
            .clip(MaterialTheme.shapes.medium),
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
//            placeholder = painterResource(R.drawable.placeholder),
        contentDescription = stringResource(R.string.article_image),
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