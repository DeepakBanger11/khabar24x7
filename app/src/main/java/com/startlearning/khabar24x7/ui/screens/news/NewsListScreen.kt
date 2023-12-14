package com.startlearning.khabar24x7.ui.screens.news


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.startlearning.khabar24x7.modal.data.TableArticle
import com.startlearning.khabar24x7.modal.data.TempTable
import com.startlearning.khabar24x7.modal.data.newsJson.Article
import com.startlearning.khabar24x7.modal.data.newsJson.VisibiltySetter
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.other.TopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
    val selectedLanguage by userPreferencesDataStore.selectedLanguageFlow.collectAsState(initial = "")
    val selectedCategories by userPreferencesDataStore.selectedCategoriesFlow.collectAsState(initial = "en")
    newsViewModel.getNewsPaging(
        selectedCategories.toString() ?: "health",
        "en"
    )
    val lazyPagingItems = newsViewModel.newsPagingList.collectAsLazyPagingItems()



    Column {
        TopBar(
            navController = navController,
            VisibiltySetter(false, true),
            newsViewModel = newsViewModel,
            userPreferencesDataStore =userPreferencesDataStore)
        LazyColumn {
            items(lazyPagingItems.itemCount) { index ->
                val article = lazyPagingItems[index]
                if (article != null) {
                    NewsItem(
                        navController = navController,
                        article = article,
                        newsViewModel = newsViewModel
                    )
                } else {
                    // Handle null article if necessary
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItem(
    navController: NavHostController,
    article: Article,
    newsViewModel: NewsViewModel
) {
    var isFabVisible by remember { mutableStateOf(false) }
    val articlesState = newsViewModel.getAllArticles.observeAsState(initial = emptyList())
    articlesState.value.forEach { item ->
        if (item.title == article.title) {
            isFabVisible = true
        } else {
        }

    }
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {
                TempArticleToDatabase(article = article, newsViewModel = newsViewModel)
                newsViewModel.setNavigation("newsDetail")
                navController.navigate("newsDetails")
            }
            .clip(RoundedCornerShape(8.dp)),
        tonalElevation = 5.dp,
        shadowElevation = 5.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9) // Aspect ratio for the image
                    .clip(RoundedCornerShape(8.dp))
            ) {
                GlideImage(
                    model = article.urlToImage,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                // setting visibility of fab
                FloatingActionButton(
                    onClick = {
                        isFabVisible = !isFabVisible
                        if (isFabVisible) {
                            AddArticleToDatabase(
                                article = article,
                                newsViewModel = newsViewModel
                            )
                        } else {
                            newsViewModel.deleteArticleByTitle(article.title)
                        }

                    },
                    elevation = FloatingActionButtonDefaults.elevation(3.dp),
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd) // Positioning at the top end of the image
                ) {
                    if (!isFabVisible) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.inverseSurface,
                            modifier = Modifier.size(40.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = Color.Red,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }


            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = article.title ?: "Default Text",
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = article.author ?: "Default Text",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }
    }
}


fun AddArticleToDatabase(
    article: Article,
    newsViewModel: NewsViewModel
) {
    var tableArticle = TableArticle(
        0,
        article.author,
        article.content,
        article.description,
        article.publishedAt,
        article.title,
        article.url,
        article.urlToImage
    )
    newsViewModel.addArticles(tableArticle)
}

fun TempArticleToDatabase(
    article: Article,
    newsViewModel: NewsViewModel
) {
    var tableArticle = TempTable(
        0,
        article.author,
        article.content,
        article.description,
        article.publishedAt,
        article.title,
        article.url,
        article.urlToImage
    )
    newsViewModel.addTempArticle(tableArticle)
}



