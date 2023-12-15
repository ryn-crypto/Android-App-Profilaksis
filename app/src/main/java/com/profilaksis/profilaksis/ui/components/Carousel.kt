package com.profilaksis.profilaksis.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.profilaksis.profilaksis.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Carousel(
    items: List<String>,
    modifier: Modifier = Modifier
) {
    var currentIndex by remember { mutableIntStateOf(0) }

    LazyRow(
        modifier = modifier
            .height(100.dp)
    ) {
        items(items.size) { index ->
            val backgroundColor = Color.LightGray

            CarouselItem(
                text = items[index],
                shape = RoundedCornerShape(8.dp),
                backgroundColor = backgroundColor,
                visible = index == currentIndex
            )
        }
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            repeatEveryInterval(1000L) {
                currentIndex = if (currentIndex == items.size - 1) {
                    0
                } else {
                    (currentIndex + 1).coerceIn(items.indices)
                }
            }
        }
    }
}

@Composable
fun CarouselItem(
    text: String,
    shape: Shape,
    backgroundColor: Color,
    visible: Boolean
) {
    if (visible) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(350.dp)
                .clip(shape)
                .background(color = backgroundColor)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Images(url = text, modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Images(url: String, modifier: Modifier) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
//            placeholder = painterResource(R.drawable.placeholder), // skeleton
        contentDescription = stringResource(R.string.article_image),
        contentScale = ContentScale.Crop,
    )
}

suspend fun repeatEveryInterval(interval: Long, action: () -> Unit) {
    while (true) {
        delay(interval)
        action()
    }
}

@Preview(showBackground = true)
@Composable
fun CarouselPreview() {
    Carousel(items = listOf("Item 1", "Item 2", "Item 3", "Item 4"))
}

