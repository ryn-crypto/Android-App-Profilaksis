package com.profilaksis.profilaksis.ui.screen.splashscreen

//import androidx.compose.animation.Crossfade
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableFloatStateOf
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.profilaksis.profilaksis.R
//import kotlinx.coroutines.delay
//
//@Composable
//fun SplashScreen(
//    navigateToNextScreen: () -> Unit,
//) {
//    var shouldShowSplash by remember { mutableStateOf(true) }
//
//    if (shouldShowSplash) {
//        SplashScreenContent(
//            navigateToNextScreen = {
//                shouldShowSplash = false
//                navigateToNextScreen()
//            }
//        )
//
//    }
//}
//
//@Composable
//fun SplashScreenContent(
//    navigateToNextScreen: () -> Unit,
//) {
//    var scale by remember { mutableFloatStateOf(0.5f) }
//    var scale2 by remember { mutableStateOf (20.dp) }
//
//    LaunchedEffect(Unit) {
//        scale = 1f
//        scale2 = 200.dp
//    }
//
//    LaunchedEffect(Unit) {
//        delay(20000)
//        navigateToNextScreen()
//    }
//
//    val animatedScale by animateFloatAsState(
//        targetValue = scale,
//        animationSpec = tween(durationMillis = 200), label = "animate"
//    )
//    val animatedScale2 by animateDpAsState(
//        targetValue = scale2,
//        animationSpec = tween(durationMillis = 200), label = "animate"
//    )
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                color = Color.Black.copy(alpha = 0.1f),
//            ),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceAround
//    ) {
//        Box(
//            modifier = Modifier
//                .size(animatedScale2)
//                .background(Color.Blue)
//                .clip(CircleShape)
//        )
//        DrawableAnimation()
//        Box(
//            modifier = Modifier
//                .size(10.dp)
//                .background(Color.Blue)
//                .graphicsLayer {
//                    scaleX = animatedScale
//                    scaleY = animatedScale
//                }
//        )
//    }
//}
//
//@Composable
//fun DrawableAnimation() {
//    val drawables = listOf(
//        R.drawable.invisible,
//        R.drawable.visible,
//        R.drawable.ic_launcher_foreground
//    )
//
//    var imageIndex by remember { mutableIntStateOf(0) }
//    val currentImage = remember { mutableIntStateOf(drawables[0]) }
//
//    LaunchedEffect(Unit) {
//        while (true) {
//            delay(2000)
//            imageIndex = (imageIndex + 1) % drawables.size
//            currentImage.intValue = drawables[imageIndex]
//        }
//    }
//
//    Crossfade(targetState = currentImage.intValue, label = "animation") { drawable ->
//        androidx.compose.foundation.Image(
//            painter = painterResource(id = drawable),
//            contentDescription = null,
//            modifier = Modifier.size(200.dp)
//        )
//    }
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun SplashScreenPreview() {
//    Surface(color = Color.White) {
//        SplashScreen(
//            navigateToNextScreen = {}
//        )
//    }
//}


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.profilaksis.profilaksis.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    val transition = remember {
        Animatable(50f)
    }

//    val transition = rememberInfiniteTransition(label = "")

    LaunchedEffect(Unit) {
        transition.animateTo(
            targetValue = 300f,
            animationSpec = tween(durationMillis = 5000, easing = FastOutSlowInEasing)
        )
    }

//    val boxSize by transition.animateFloat(
//        initialValue = 0f,
//        targetValue = 300f,
//        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing), label = ""
//
//    )

    val drawables = listOf(
        R.drawable.invisible,
        R.drawable.visible,
        R.drawable.ic_launcher_foreground
    )

    var imageIndex by remember { mutableStateOf(0) }
    val currentImage = remember { mutableStateOf(drawables[0]) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(800)
            imageIndex = (imageIndex + 1) % drawables.size
            currentImage.value = drawables[imageIndex]
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(transition.value.dp)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = currentImage.value),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo",
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
