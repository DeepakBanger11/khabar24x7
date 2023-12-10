package com.startlearning.khabar24x7.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.startlearning.khabar24x7.modal.data.newsJson.Article
import com.startlearning.khabar24x7.modal.data.newsJson.Source
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.R

val savedArticles = listOf(
    Article(
        author = "John Doe",
        title = "Sample Article 1",
        urlToImage = "https://www.journaldugeek.com/app/uploads/2023/12/god9.jpg",
        content = "",
        description = "",
        publishedAt = "",
        source = Source("", ""),
        url = ""
    ),
    Article(
        author = "Jane Smith",
        title = "Sample Article 2",
        urlToImage = "https://www.presse-citron.net/app/uploads/2023/11/walking-dead-destinies-rick.jpg",
        content = "",
        description = "",
        publishedAt = "",
        source = Source("", ""),
        url = ""
    )
    // Add more articles as needed
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateToMyNewsListScreen: () -> Unit,
    navigateToHomeScreen: () -> Unit,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
    val email by userPreferencesDataStore.email.collectAsState(initial = "test")
    val password by userPreferencesDataStore.password.collectAsState(initial = "test")
    val navigation by userPreferencesDataStore.navigation.collectAsState(initial = "home")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                        if(navigation == "home")
                        { navigateToHomeScreen() }
                        else{ navigateToMyNewsListScreen() }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                    )
                }
            },

            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
               // .background(color = MaterialTheme.colorScheme.surfaceContainerHighest)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Email :- $email",
                    fontSize = 20.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
                Text(
                    text = "Password:- $password",
                    fontSize = 20.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Saved Articles Section
        SavedArticlesSortingBar()
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(savedArticles) { article ->
                SavedArticleItem(article)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SavedArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 10.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerLow)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = article.urlToImage,
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = article.title)
            // Add more details about the article as needed
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedArticlesSortingBar() {
    Surface(
        tonalElevation = 8.dp,
        shadowElevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Saved Articles",
                    fontSize = 18.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                )
            },
            actions = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    IconButton(
                        onClick = { /* Handle sorting by added date */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Sort by Added Date"
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(
                        onClick = { /* Handle sorting alphabetically */ }
                    ) {
                        Icon(
                            imageVector = Icons.Default.SortByAlpha,
                            contentDescription = "Sort Alphabetically"
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )
        )
    }
}
