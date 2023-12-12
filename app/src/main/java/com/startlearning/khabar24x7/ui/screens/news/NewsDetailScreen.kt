package com.startlearning.khabar24x7.ui.screens.news

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.startlearning.khabar24x7.modal.data.VisibiltySetter
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.other.TopBar

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailScreen(
    navController: NavHostController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
    val selectedArticle by newsViewModel.selectedArticle.observeAsState()
    val navigation by userPreferencesDataStore.navigation.collectAsState(initial = "")
    var dataStoredArticle  by remember { mutableStateOf<TableArticle?>(null) }
    LaunchedEffect(key1 = Unit) {
        val article = newsViewModel.getArticle()
        dataStoredArticle = article
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(navController = navController, VisibiltySetter(false,true))
        if (navigation == "fromAPI") {
            dataStoredArticle?.let { NewsDetailList(it) }
        }
        else{
            selectedArticle?.let { NewsDetailList(it) }
        }
//        Text(text = dataStoredArticle.toString())
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsDetailList(selectedArticle:TableArticle){
    val context = LocalContext.current
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {

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