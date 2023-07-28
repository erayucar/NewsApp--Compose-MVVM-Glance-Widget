package com.erayucar.newsapp.presentation.article_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.erayucar.newsapp.domain.model.Article

@Composable
fun ArticleListItem(article: Article, onItemClick: (Article) -> Unit) {

    Column(modifier = Modifier
        .fillMaxSize()
        .clickable { onItemClick(article) }
        .padding(16.dp)) {
        Text(text = article.author, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall, color = Color.LightGray)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.title, style = MaterialTheme.typography.bodyLarge, color = Color.White)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = article.publishedAt,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

    }
}

@Composable
fun Categories(categoryName: String, onItemClick: (String) -> Unit, painter: Painter) {
    val gradientVertically = Brush.verticalGradient(

        startY = 0.1f,
        colors = listOf(Color(0xFFFFFFFF), Color(0xF7FD1A09))
    )
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0x117C6969)),
        modifier = Modifier
            .height(90.dp)
            .width(90.dp)
            .border(1.dp , brush = gradientVertically,CircleShape)
            .clickable { onItemClick(categoryName) }, shape = RoundedCornerShape(500.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = categoryName,
                color = Color(0xFFFFFFFF),
                style = MaterialTheme.typography.bodySmall
            )
            Image(
                painter = painter, contentDescription = "Category Image", modifier = Modifier
                    .height(100.dp)
            )
        }
    }
}


    
    


    
    



