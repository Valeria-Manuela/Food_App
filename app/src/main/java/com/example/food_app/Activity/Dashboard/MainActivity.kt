package com.example.food_app.Activity.Dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.food_app.Domain.CategoryModel
import androidx.compose.ui.unit.sp
import com.example.food_app.Domain.FoodModel
import com.example.food_app.R
import com.example.food_app.ViewModel.MainViewModel
import com.example.food_app.ui.theme.Food_AppTheme
import com.google.firebase.FirebaseApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        enableEdgeToEdge()
        setContent {
            Food_AppTheme {
                MainScreen()
            }
        }
    }

}

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()

    val categoryList by viewModel.loadCategory().observeAsState(initial = emptyList())
    val bestFoodList by viewModel.loadBestFood()
        .observeAsState(initial = emptyList<FoodModel>())

    val categories = remember { mutableStateListOf<CategoryModel>() }
    val bestFood = remember { mutableStateListOf<FoodModel>() }


    var showBestFoodLoading by remember { mutableStateOf(true) }

    categories.clear()
    categories.addAll(categoryList)

    bestFood.clear()
    bestFood.addAll(bestFoodList)

    showBestFoodLoading = bestFood.isEmpty()
    Scaffold(
        bottomBar = { MyBottomBar() }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.lightGrey))
                .padding(paddingValues)
        ) {
            item(span = { GridItemSpan(2) }) {
                TopBar()
            }

            item(span = { GridItemSpan(2) }) {
                CategorySection(
                    categories = categories,
                    showCategoryLoading = categories.isEmpty()
                )
            }

            item(span = { GridItemSpan(2) }) {
                Text(
                    text ="Comidas para você",
                    color = colorResource(R.color.darkPurple),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            if(showBestFoodLoading){

                item(span = { GridItemSpan(2) }){

                    Box(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                            contentAlignment = Alignment.Center
                    ){

                        CircularProgressIndicator()
                    }

                }


            }else {
                itemsIndexed(bestFood) { index, item ->
                    FoodItemCardGrid(item = item)
                }
            }

        }
    }
}
