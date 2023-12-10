package com.startlearning.khabar24x7.ui.screens.news


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.startlearning.khabar24x7.modal.data.newsJson.Article
import com.startlearning.khabar24x7.modal.data.newsJson.Source
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    navigateToMyNewsListScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    navigateToProfileScreen: () -> Unit,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
    val selectedLanguage by userPreferencesDataStore.selectedLanguageFlow.collectAsState(initial = "")
    val selectedCategories by userPreferencesDataStore.selectedCategoriesFlow.collectAsState(initial = "en")

    val lazyPagingItems = newsViewModel.getNewsPaging(
        selectedCategories.toString() ?: "health",
        "en"
    ).collectAsLazyPagingItems()
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Khabar24x7",
                    color = Color.White
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigateToHomeScreen()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }
            },
            actions = {
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .size(36.dp)
                ) {
                    IconButton(
                        onClick = {
                            newsViewModel.setNavigation("newsList")
                            navigateToProfileScreen()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = Color.White,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
        LazyColumn {
            items(lazyPagingItems.itemCount) { index ->
                val article = lazyPagingItems[index]
                if (article != null) {
                    NewsItem(article = article)
                } else {
                    // Handle null article if necessary
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsItem(article: Article) {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
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
                // FAB positioned at the top of the image
                FloatingActionButton(
                    onClick = { /* Handle FAB click */ },
                    elevation = FloatingActionButtonDefaults.elevation(3.dp),
                    containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopEnd) // Positioning at the top end of the image
                ) {
                    // Add your FAB icon or content here
                    Text(text = "+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = article.author,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    val article = Article(
        author = "John Doe",
        title = "Sample Article Title",
        urlToImage = "https://via.placeholder.com/300.png",
        content = "",
        description = "",
        publishedAt = "",
        source = Source("", ""),
        url = ""
    )


    NewsItem(article = article)
}


