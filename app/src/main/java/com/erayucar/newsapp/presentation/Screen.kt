package com.erayucar.newsapp.presentation

sealed class Screen(val route: String) {
    object articleListScreen : Screen("article_list_screen")

    object articleDetailScreen : Screen("article_detail_screen")

    object splashScreen : Screen("splash_screen")



}