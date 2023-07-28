package com.erayucar.newsapp.presentation.article_detail

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArticleDetailScreen(
    navController: NavController,
    onClick: () -> Unit,
    articleUrl: String


) {

    //val article = navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "News Web View", color = Color.White) },
                    navigationIcon = {

                        IconButton(onClick = { onClick()}) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color(0xFFFFFFFF)
                            )
                        }


                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors( containerColor = Color(0xFF16171B)
                    )
                )
            }) {
            ArticleWebview(articleUrl!!)
        }


    }
}


@Composable
fun ArticleWebview(murl: String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var backEnabled = remember { mutableStateOf(false) }
        var webView: WebView? = null
        AndroidView(
            factory = {

                WebView(it).apply {
                    settings.javaScriptEnabled = true
                    layoutParams = ViewGroup.LayoutParams(

                        ViewGroup.MarginLayoutParams.MATCH_PARENT,
                        ViewGroup.MarginLayoutParams.MATCH_PARENT,

                        )

                    webViewClient = WebViewClient()
                    loadData(murl, "text/html", "UTF-8")

                }

            },
            update = {
                it.loadUrl(murl)
            },
        )
        BackHandler(enabled = backEnabled.value) {
            webView?.goBack()
        }
    }
    // Declare a string that contains a url


    // Adding a WebView inside AndroidView
    // with layout as full screen

}