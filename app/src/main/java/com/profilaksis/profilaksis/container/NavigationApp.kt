package com.profilaksis.profilaksis.container

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.profilaksis.profilaksis.R
import com.profilaksis.profilaksis.data.model.UserLogin
import com.profilaksis.profilaksis.ui.navigation.NavigationItem
import com.profilaksis.profilaksis.ui.navigation.Screen
import com.profilaksis.profilaksis.ui.screen.history.HistoryScreen
import com.profilaksis.profilaksis.ui.screen.home.HomeScreen
import com.profilaksis.profilaksis.ui.screen.profile.ProfileScreen
import com.profilaksis.profilaksis.ui.theme.ProfilaksisTheme
import com.profilaksis.profilaksis.utils.DEFAULT_PADDING
import com.profilaksis.profilaksis.utils.times
import com.profilaksis.profilaksis.utils.transform
import kotlin.math.PI
import kotlin.math.sin

@RequiresApi(Build.VERSION_CODES.S)
private fun getRenderEffect(): RenderEffect {
    val blurEffect = RenderEffect
        .createBlurEffect(80f, 80f, Shader.TileMode.MIRROR)

    val alphaMatrix = RenderEffect.createColorFilterEffect(
        ColorMatrixColorFilter(
            ColorMatrix(
                floatArrayOf(
                    1f, 0f, 0f, 0f, 0f,
                    0f, 1f, 0f, 0f, 0f,
                    0f, 0f, 1f, 0f, 0f,
                    0f, 0f, 0f, 50f, -5000f
                )
            )
        )
    )

    return RenderEffect
        .createChainEffect(alphaMatrix, blurEffect)
}

@Composable
fun ProfilaksisApp(
    modifier: Modifier = Modifier,
    clickFab: (String) -> Unit = { },
    navController: NavHostController = rememberNavController(),
    userData: UserLogin,
    onClick: () -> Unit = { },
    context: Context,
    token: UserLogin?,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(bottom = 10.dp)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(userData = userData, context = context)
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    userData = userData,
                    onCLick = onClick
                )
            }
            composable(Screen.History.route) {
                HistoryScreen(token = token, context = context)
            }
        }

        if (currentRoute != Screen.Result.route) {
            BottomNavigation(clickFab, navController)
        }
    }
}

@Composable
fun BottomNavigation(
    clickFab: (String) -> Unit = { },
    navController: NavHostController,
) {
    val isMenuExtended = remember { mutableStateOf(false) }

    val fabAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ), label = ""
    )

    val clickAnimationProgress by animateFloatAsState(
        targetValue = if (isMenuExtended.value) 1f else 0f,
        animationSpec = tween(
            durationMillis = 400,
            easing = LinearEasing
        ), label = ""
    )

    val renderEffect = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        getRenderEffect().asComposeRenderEffect()
    } else {
        null
    }

    ItemNavigation(
        clickFab = clickFab,
        navController = navController,
        renderEffect = renderEffect,
        fabAnimationProgress = fabAnimationProgress,
        clickAnimationProgress = clickAnimationProgress
    ) {
        isMenuExtended.value = isMenuExtended.value.not()
    }

}

@Composable
fun ItemNavigation(
    clickFab: (String) -> Unit = { },
    navController: NavHostController,
    renderEffect: androidx.compose.ui.graphics.RenderEffect?,
    fabAnimationProgress: Float = 0f,
    clickAnimationProgress: Float = 0f,
    toggleAnimation: () -> Unit = { },
) {
    Box(
        contentAlignment = Alignment.BottomCenter
    ) {
        CustomBottomNavigation(navController)
        Circle(
            color = MaterialTheme.colors.primary.copy(alpha = 0.5f),
            animationProgress = 0.5f
        )

        FabGroup(renderEffect = renderEffect, animationProgress = fabAnimationProgress)
        FabGroup(
            clickFab = clickFab,
            navController = navController,
            renderEffect = null,
            animationProgress = fabAnimationProgress,
            toggleAnimation = toggleAnimation
        )
        Circle(
            color = Color.White,
            animationProgress = clickAnimationProgress
        )
    }
}

@Composable
fun Circle(color: Color, animationProgress: Float) {
    val animationValue = sin(PI * animationProgress).toFloat()

    Box(
        modifier = Modifier
            .padding(DEFAULT_PADDING.dp)
            .size(56.dp)
            .scale(2 - animationValue)
            .border(
                width = 2.dp,
                color = color.copy(alpha = color.alpha * animationValue),
                shape = CircleShape
            )
    )
}

@Composable
fun CustomBottomNavigation(navController: NavHostController) {
    val navigationItem = listOf(
        NavigationItem(
            iconIdle = painterResource(R.drawable.history_idle),
            iconActive = painterResource(R.drawable.history_active),
            title = "History",
            contentDescription = "History",
            screen = Screen.History
        ),
        NavigationItem(
            iconIdle = painterResource(R.drawable.profile_idle),
            iconActive = painterResource(R.drawable.profile_active),
            title = "Profile",
            contentDescription = "Profile",
            screen = Screen.Profile
        )
    )
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(70.dp)
            .paint(
                painter = painterResource(R.drawable.bottom_navigation),
                contentScale = ContentScale.FillWidth
            )
            .padding(horizontal = 40.dp)
    ) {
        navigationItem.forEach { item ->
            IconButton(onClick = {
                navController.navigate(item.screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }) {
                if (navController.currentDestination?.route == item.screen.route) {
                    Icon(
                        painter = item.iconActive,
                        contentDescription = item.title,
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                } else {
                    Icon(
                        painter = item.iconIdle,
                        contentDescription = item.title,
                        tint = Color.White.copy(alpha = 0.5f),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun FabGroup(
    clickFab: (String) -> Unit = { },
    navController: NavHostController? = null,
    animationProgress: Float = 0f,
    renderEffect: androidx.compose.ui.graphics.RenderEffect? = null,
    toggleAnimation: () -> Unit = { },
) {
    Box(
        Modifier
            .fillMaxSize()
            .graphicsLayer { this.renderEffect = renderEffect }
            .padding(bottom = DEFAULT_PADDING.dp),
        contentAlignment = Alignment.BottomCenter
    ) {

        AnimatedFab(
            icon = painterResource(R.drawable.heart),
            modifier = Modifier
                .padding(
                    PaddingValues(
                        bottom = 72.dp,
                        end = 120.dp
                    ) * FastOutSlowInEasing.transform(0f, 0.8f, animationProgress)
                ),
            opacity = LinearEasing.transform(0.2f, 0.7f, animationProgress),
            onClick = {
                clickFab("heart")
            }
        )

        AnimatedFab(
            icon = painterResource(R.drawable.diabetes),
            modifier = Modifier.padding(
                PaddingValues(
                    bottom = 72.dp,
                    start = 120.dp
                ) * FastOutSlowInEasing.transform(0.2f, 1.0f, animationProgress)
            ),
            opacity = LinearEasing.transform(0.4f, 0.9f, animationProgress),
            onClick = {
                clickFab("diabetes")
            }
        )

        AnimatedFab(
            modifier = Modifier
                .scale(1f - LinearEasing.transform(0.5f, 0.85f, animationProgress)),
        )

        if (navController != null && navController.currentDestination?.route != Screen.Home.route) {
            AnimatedFab(
                icon = painterResource(R.drawable.home),
                modifier = Modifier
                    .rotate(
                        225 * FastOutSlowInEasing
                            .transform(0.35f, 0.65f, animationProgress)
                    ),
                onClick = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                backgroundColor = Color.Gray.copy(alpha = 0.5f),
            )
        } else {
            var isIconOneVisible by remember { mutableStateOf(true) }

            val currentIcon = if (isIconOneVisible) {
                painterResource(R.drawable.scan)
            } else {
                painterResource(R.drawable.plus)
            }
            AnimatedFab(
                icon = currentIcon,
                modifier = Modifier
                    .rotate(
                        225 * FastOutSlowInEasing
                            .transform(0.35f, 0.65f, animationProgress)
                    ),
                onClick = {
                    isIconOneVisible = !isIconOneVisible
                    toggleAnimation()
                },
                backgroundColor = Color.Gray.copy(alpha = 0.5f),
            )
        }

    }
}

@Composable
fun AnimatedFab(
    modifier: Modifier,
    icon: Painter? = null,
    opacity: Float = 1f,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    onClick: () -> Unit = {},
) {
    FloatingActionButton(
        onClick = onClick,
        elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp),
        backgroundColor = backgroundColor,
        modifier = modifier.scale(1.25f)
    ) {
        icon?.let {
            Icon(
                painter = it,
                contentDescription = null,
                tint = Color.White.copy(alpha = opacity)
            )
        }
    }
}


@Composable
@Preview(device = "id:pixel_4a", showBackground = true, backgroundColor = 0xFF3A2F6E)
private fun MainScreenPreview() {
    ProfilaksisTheme {
        ProfilaksisApp(
            clickFab = { },
            userData = UserLogin(
                1,
                "test",
                "test",
                "test",
                "test",
                "test",
            ),
            onClick = { },
            context = LocalContext.current,
            token = UserLogin(
                1,
                "test",
                "test",
                "test",
                "test",
                "test",
            )
        )
    }
}