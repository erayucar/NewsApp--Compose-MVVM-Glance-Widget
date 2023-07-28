package com.erayucar.newsapp.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.erayucar.newsapp.R

@Composable
fun SplashScreen() {
    Column() {
        LottieAnimation()


    }
}
@Composable
fun LottieAnimation(){

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.news))

    com.airbnb.lottie.compose.LottieAnimation(composition = composition, modifier = Modifier.fillMaxSize())
}