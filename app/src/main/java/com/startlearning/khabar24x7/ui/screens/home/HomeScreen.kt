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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.startlearning.khabar24x7.R
import com.startlearning.khabar24x7.modal.data.NewsCategories
import com.startlearning.khabar24x7.modal.data.newsJson.VisibiltySetter
import com.startlearning.khabar24x7.modal.dataStore.UserPreferencesDataStore
import com.startlearning.khabar24x7.modal.viewModal.NewsViewModel
import com.startlearning.khabar24x7.ui.screens.other.TopBar


@Composable
fun HomeScreen(
    navController: NavController,
    newsViewModel: NewsViewModel,
    userPreferencesDataStore: UserPreferencesDataStore
) {
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

    Column {
        TopBar(
            navController = navController,
            VisibiltySetter(false,false),
            newsViewModel = newsViewModel,
            userPreferencesDataStore =userPreferencesDataStore)
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
                        CategoryCard(
                            category = category,
                            navController = navController,
                            newsViewModel = newsViewModel
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp)) // Add spacing between rows
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryCard(
    category: NewsCategories,
    navController: NavController,
    newsViewModel: NewsViewModel
) {
    // add it to display data in required categories
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(160.dp)
            .height(160.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = {
            navController.navigate("newsList")
            newsViewModel.setNavigation("newsList")
            newsViewModel.toggleCategory(category.title)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = category.imageResource,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
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


/*@Composable
fun CountryDropDown(
    newsViewModel: NewsViewModel
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val density = LocalDensity.current.density
    var selectedLanguage by remember { mutableStateOf("") }
    val countries =
        listOf("Select Language", "en", "fr", "es", "he", "ru") // Add more countries as needed
    var selectedCountry by remember { mutableStateOf(countries[0]) }

    Row {
        Spacer(modifier = Modifier.width(16.dp))

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.wrapContentSize()
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = country,
                            fontSize = 14.sp
                            //color = Color.White
                        )
                    },
                    onClick = {
                        selectedCountry = country
                        expanded = false
                        // Perform actions based on the selected country if needed
                        // For example, you can navigate or perform some functionality here
                        if (country != "Select Country") {
                            newsViewModel.setSelectedLanguage(country)
                            // e.g., navigate to a specific screen
                        }
                    }
                )


            }
        }

        Box(
            modifier = Modifier
                .selectable(
                    selected = expanded,
                    onClick = { expanded = !expanded }
                )
        ) {
            Text(
                text = selectedCountry,
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
                fontSize = 16.sp,
                color = Color.White
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier.padding(start = 80.dp / density)
            )
        }
    }
}*/

