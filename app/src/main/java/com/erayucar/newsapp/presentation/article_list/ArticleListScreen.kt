package com.erayucar.newsapp.presentation.article_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.erayucar.newsapp.R
import com.erayucar.newsapp.presentation.Screen
import com.erayucar.newsapp.presentation.viewmodel.ArticleListViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
    navController: NavController
) {
    val categoryName = remember {
        mutableStateOf("General")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            LazyRow(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp), content = {
                item(7) {
                    Categories(
                        categoryName = "Science",
                        onItemClick = {
                            viewModel.loadArticles("science", "tr")
                            categoryName.value = "Science"
                        },
                        painter = painterResource(id = R.drawable.baseline_science_24)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Categories(
                        categoryName = "Health",
                        onItemClick = {
                            viewModel.loadArticles("health", "tr")
                            categoryName.value = "Health"
                        },
                        painter = painterResource(id = R.drawable.baseline_health_and_safety_24)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    Categories(
                        categoryName = "Business",
                        onItemClick = {
                            viewModel.loadArticles("business", "tr")
                            categoryName.value = "Business"
                        },
                        painter = painterResource(id = R.drawable.baseline_business_center_24)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    Categories(
                        categoryName = "Entertainment",
                        onItemClick = {
                            viewModel.loadArticles("entertainment", "tr")
                            categoryName.value = "Entertainment"
                        },
                        painter = painterResource(id = R.drawable.baseline_movie_filter_24)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    Categories(
                        categoryName = "Sports",
                        onItemClick = {
                            viewModel.loadArticles("sports", "tr")
                            categoryName.value = "Sports"
                        },
                        painter = painterResource(id = R.drawable.baseline_sports_soccer_24)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))

                    Categories(
                        categoryName = "Technology",
                        onItemClick = {
                            viewModel.loadArticles("technology", "tr")
                            categoryName.value = "Technology"
                        },
                        painter = painterResource(id = R.drawable.baseline_electric_car_24)
                    )
                }

            })
            Text(
                text = categoryName.value,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                viewModel.articleState.value.articles?.let { articles ->
                    items(articles) { article ->
                        ArticleListItem(
                            article = article,
                            onItemClick = { article ->
                                // another screen

                               /* navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "article",
                                    value = article
                                )*/

                                val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                                navController.navigate(Screen.articleDetailScreen.route + "/${encodedUrl}")
                            })
                    }

                }


            }
        }

    }
}
