package com.profilaksis.profilaksis.ui.screen.splashscreen

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profilaksis.profilaksis.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToNextScreen: () -> Unit,
) {
    var shouldShowSplash by remember { mutableStateOf(true) }

    if (shouldShowSplash) {
        SplashScreenContent(
            navigateToNextScreen = {
                shouldShowSplash = false
                navigateToNextScreen()
            }
        )

    }
}

@Composable
fun SplashScreenContent(
    navigateToNextScreen: () -> Unit,
) {
    var scale by remember { mutableFloatStateOf(0.5f) }
    var scale2 by remember { mutableStateOf (20.dp) }

    LaunchedEffect(Unit) {
        scale = 1f
        scale2 = 200.dp
    }

    LaunchedEffect(Unit) {
        delay(20000)
        navigateToNextScreen()
    }

    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(durationMillis = 200), label = "animate"
    )
    val animatedScale2 by animateDpAsState(
        targetValue = scale2,
        animationSpec = tween(durationMillis = 200), label = "animate"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.Black.copy(alpha = 0.1f),
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .size(animatedScale2)
                .background(Color.Blue)
                .clip(CircleShape)
        )
        DrawableAnimation()
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(Color.Blue)
                .graphicsLayer {
                    scaleX = animatedScale
                    scaleY = animatedScale
                }
        )
    }
}

@Composable
fun DrawableAnimation() {
    val drawables = listOf(
        R.drawable.invisible,
        R.drawable.visible,
        R.drawable.ic_launcher_foreground
    )

    var imageIndex by remember { mutableIntStateOf(0) }
    val currentImage = remember { mutableIntStateOf(drawables[0]) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            imageIndex = (imageIndex + 1) % drawables.size
            currentImage.intValue = drawables[imageIndex]
        }
    }

    Crossfade(targetState = currentImage.intValue, label = "animation") { drawable ->
        androidx.compose.foundation.Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    Surface(color = Color.White) {
        SplashScreen(
            navigateToNextScreen = {}
        )
    }
}