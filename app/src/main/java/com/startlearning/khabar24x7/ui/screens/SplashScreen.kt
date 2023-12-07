package com.startlearning.khabar24x7.ui.screens


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startlearning.khabar24x7.R
import com.startlearning.khabar24x7.utils.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navigateToLoginScreen:() -> Unit) {

    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetState by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else 200.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val onsetState by animateDpAsState(
        targetValue = if (startAnimation) 0.dp else -200.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToLoginScreen()
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.newslogo), // Your login background image
            contentDescription = "Login Background",
            modifier = Modifier
                .width(200.dp)
                .height(150.dp)
                .offset(y = onsetState)
                .alpha(alpha = alphaState),
            contentScale = ContentScale.Fit
        )
        Spacer(
            modifier = Modifier
                .height(32.dp)
                .offset(y = onsetState)
                .alpha(alpha = alphaState),
        )
        Text(
            text = "Khabar 24x7",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 24.sp
        )
    }
}


//@Preview
//@Composable
//fun SplashScreenPreview() {
//    SplashScreen()
//}
