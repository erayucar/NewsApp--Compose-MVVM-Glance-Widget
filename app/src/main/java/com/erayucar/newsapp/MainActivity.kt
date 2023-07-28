package com.erayucar.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erayucar.newsapp.presentation.Screen
import com.erayucar.newsapp.presentation.article_detail.ArticleDetailScreen
import com.erayucar.newsapp.presentation.article_list.ArticleListScreen
import com.erayucar.newsapp.presentation.splash.SplashScreen
import com.erayucar.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val destination = intent.extras?.getString("destinationKey") ?: ""

        val activityScope = CoroutineScope(Dispatchers.Main)


        setContent {
            NewsAppTheme {
                val gradientVertically = Brush.verticalGradient(

                    startY = 0.1f,
                    colors = listOf(Color(0xFF16171B), Color(0xF7FD1A09))

                )
                val navController = rememberNavController()



                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(brush = gradientVertically),
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = Screen.splashScreen.route
                    ) {
                        composable(Screen.splashScreen.route) {
                            SplashScreen()
                        }
                        composable(Screen.articleListScreen.route) {
                            EnterAnimation {
                                ArticleListScreen(navController = navController)

                            }
                        }
                        composable(route = Screen.articleDetailScreen.route + "/{articleUrl}",
                            arguments = listOf(
                                navArgument("articleUrl") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val articleUrl = remember {
                                it.arguments?.getString("articleUrl")
                            }
                            EnterAnimation {
                                ArticleDetailScreen(
                                    navController = navController,
                                    articleUrl = articleUrl!!,
                                    onClick = { navController.navigate(Screen.articleListScreen.route) })

                            }

                        }
                    }


                    if (destination.isNotEmpty()) {
                        val decodedDestination = URLEncoder.encode(destination, StandardCharsets.UTF_8.toString())
                        navController.navigate(Screen.articleDetailScreen.route+ "/${decodedDestination}")
                    }else{
                        LaunchedEffect(key1 = true, ) {
                            activityScope.launch {
                                delay(2000)
                                navController.navigate(Screen.articleListScreen.route)
                            }
                        }
                    }


                }
            }
        }
    }
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterAnimation(content: @Composable () -> Unit) {
        AnimatedVisibility(
            visible = true,
            enter = slideInHorizontally(
                initialOffsetX = { 40 }
            ) + expandHorizontally (
                expandFrom = Alignment.CenterHorizontally
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutHorizontally() + slideOutHorizontally() + fadeOut(),
            content = content,
            initiallyVisible = false
        )
    }

}


