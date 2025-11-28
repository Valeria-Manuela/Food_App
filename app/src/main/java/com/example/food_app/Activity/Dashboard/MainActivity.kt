package com.example.food_app.Activity.Dashboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

import com.example.food_app.R
import com.example.food_app.ui.theme.Food_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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



    Scaffold(
        bottomBar = { MyBottomBar() }
    ) { paddingValues ->

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.lightGrey))
                .padding(paddingValues),
            contentPadding = paddingValues,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item(span = { GridItemSpan(2) }) {
                TopBar()
            }

//            item(span = { GridItemSpan(2) }) {
//                CategorySection(
//                    categories = categories.toMutableList() as SnapshotStateList<CategoryModel>,
//                    showCategoryLoading = showCategoryLoading
//                )
//            }
        }
    }

}
