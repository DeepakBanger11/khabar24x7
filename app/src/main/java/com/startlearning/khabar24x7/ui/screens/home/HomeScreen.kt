package com.startlearning.khabar24x7.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.startlearning.khabar24x7.R
import com.startlearning.khabar24x7.modal.data.NewsCategories


@Composable
fun HomeScreen() {
    val newsCategories = listOf(
        NewsCategories("Headlines", painterResource(id = R.drawable.headlines)), ///top-headlines
        NewsCategories("Business", painterResource(id = R.drawable.business)),
        NewsCategories("Entertainment", painterResource(id = R.drawable.entertainment)),
        NewsCategories("General", painterResource(id = R.drawable.general)),
        NewsCategories("Health", painterResource(id = R.drawable.health)),
        NewsCategories("Science", painterResource(id = R.drawable.science)),
        NewsCategories("Sports", painterResource(id = R.drawable.sports)),
        NewsCategories("Technology", painterResource(id = R.drawable.technology))
    )
    val chunkedCategories = newsCategories.chunked(2)

    LazyColumn(
        modifier = Modifier.padding(15.dp)
    ) {
        items(chunkedCategories.size) { index ->
            val chunk = chunkedCategories[index]
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                chunk.forEach { category ->
                    CategoryCard(category = category)
                }
            }
            Spacer(modifier = Modifier.height(8.dp)) // Add spacing between rows
        }
    }
}


@Composable
fun CategoryCard(category: NewsCategories) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp)
            .height(160.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = category.imageResource,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.title,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    HomeScreen()
}