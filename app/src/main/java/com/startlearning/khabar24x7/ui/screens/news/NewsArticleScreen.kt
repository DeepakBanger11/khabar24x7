package com.startlearning.khabar24x7.ui.screens.news

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.startlearning.khabar24x7.modal.data.TableArticle
import com.startlearning.khabar24x7.modal.data.TempTable
import com.startlearning.khabar24x7.modal.data.newsJson.VisibiltySetter
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.other.TopBar


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsArticleScreen(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
    val context = LocalContext.current
    val navigation by userPreferencesDataStore.navigation.collectAsState(initial = "home")
    val articleState = newsViewModel.getArticle.observeAsState(initial = emptyList())
    val article:List<TempTable> = articleState.value
    val selectedArticle = article.firstOrNull()
    Column {
        TopBar(
            navController = navController,
            VisibiltySetter(false,true),
            newsViewModel = newsViewModel,
            userPreferencesDataStore =userPreferencesDataStore
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Text(
                text = selectedArticle?.title.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            GlideImage(
                model = selectedArticle?.urlToImage,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Author :${selectedArticle?.author.toString()}",
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Text(
                text = "Publish at :${selectedArticle?.publishedAt.toString()}",
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            ) {
                item {
                    Text(
                        text = selectedArticle?.description.toString(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = selectedArticle?.content.toString(),
                        fontSize = 18.sp,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    Text(
                        text = selectedArticle?.url.toString(),
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(selectedArticle?.url.toString()))
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}


