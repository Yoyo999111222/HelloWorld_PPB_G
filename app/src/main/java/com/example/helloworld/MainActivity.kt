package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldWithAnimation()
        }
    }
}

@Composable
fun HelloWorldWithAnimation() {
    // Fade & scale animation
    val scale = remember { Animatable(0f) }
    val alpha = remember { Animatable(0f) }

    // Background color state
    var bgColor by remember { mutableStateOf(randomColor()) }

    // Animate on launch
    LaunchedEffect(Unit) {
        launch { scale.animateTo(1f, tween(1000, easing = LinearOutSlowInEasing)) }
        launch { alpha.animateTo(1f, tween(1000)) }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)
            bgColor = randomColor()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Text(
            text = "Hello, World!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = scale.value,
                    scaleY = scale.value,
                    alpha = alpha.value
                )
        )
    }
}

fun randomColor(): Color {
    val r = Random.nextFloat()
    val g = Random.nextFloat()
    val b = Random.nextFloat()
    return Color(r, g, b)
}
