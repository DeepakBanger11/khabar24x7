package com.startlearning.khabar24x7.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExitToApp
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.startlearning.khabar24x7.R
import com.startlearning.khabar24x7.modal.data.TableArticle
import com.startlearning.khabar24x7.modal.data.newsJson.VisibiltySetter
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.other.AppBarFilter
import com.startlearning.khabar24x7.ui.screens.other.TopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
    val email by userPreferencesDataStore.email.collectAsState(initial = "test")
    val password by userPreferencesDataStore.password.collectAsState(initial = "test")
    val navigation by userPreferencesDataStore.navigation.collectAsState(initial = "home")
    val articlesState = newsViewModel.getAllArticles.observeAsState(initial = emptyList())
    val articles = articlesState.value
    var searchQuery by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            navController = navController,
            VisibiltySetter(false, true),
            newsViewModel = newsViewModel,
            userPreferencesDataStore = userPreferencesDataStore
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            // .background(color = MaterialTheme.colorScheme.surfaceContainerHighest)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
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
                        modifier = Modifier,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "Password:- $password",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                // Logout Button
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(10.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.background)
                        .clickable {
                            newsViewModel.setNavigation("login")
                            navController.navigate("login")
                        }
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        // Saved Articles Section
        AppBarFilter(
            onSortClick = { /*TODO*/ },
            onSearchTextChanged = { query -> searchQuery = query },
            searchQuery = searchQuery
        )
        //SavedArticlesSortingBar()
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            // Filter the articles based on searchQuery
            val filteredArticles = if (searchQuery.isNotBlank()) {
                articles.filter { article ->
                    article.title?.contains(searchQuery, ignoreCase = true) == true ||
                            article.description?.contains(searchQuery, ignoreCase = true) == true ||
                            article.author?.contains(searchQuery, ignoreCase = true) == true
                }
            } else {
                articles
            }

            items(filteredArticles.size) { index ->
                val likedArticle = filteredArticles[index]


                Card(
                    onClick = {
                        newsViewModel.getSelectedArticle(likedArticle.id)
                        newsViewModel.setNavigation("newsDetails")
                        navController.navigate("newsDetails")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 2.dp, horizontal = 2.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerLow)
                ) {
                    SavedArticleItem(
                        newsViewModel = newsViewModel,
                        article = likedArticle
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun SavedArticleItem(
    newsViewModel: NewsViewModel,
    article: TableArticle
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .shadow(10.dp),
            elevation = CardDefaults.cardElevation(20.dp),
        ) {
            GlideImage(
                model = article.urlToImage,
                contentDescription = "",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = article.title,
            maxLines = 3,
            modifier = Modifier.weight(2f)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                newsViewModel.deleteArticleByTitle(article.title)
            }
        ) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "Delete")
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
                containerColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}
