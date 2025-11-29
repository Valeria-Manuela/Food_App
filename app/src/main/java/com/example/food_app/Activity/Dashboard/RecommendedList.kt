package com.example.food_app.Activity.Dashboard

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.food_app.Activity.FoodDetail.FoodDetailsActivity

import com.example.food_app.ViewModel.MainViewModel

@Composable
fun RecommendedList(viewModel: MainViewModel) {
    val context = LocalContext.current

    val foods by viewModel.loadBestFood().observeAsState(initial = emptyList())

    val showLoading = remember { mutableStateOf(true) }

    LaunchedEffect(foods) {
        showLoading.value = foods.isEmpty()
    }

    if (showLoading.value) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp)
        ) {
            items(foods.size) { index ->
                AsyncImage(
                    model = foods[index].ImagePath,
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            val intent = Intent(context, FoodDetailsActivity::class.java).apply {
                                putExtra("object", foods[index])
                            }
                            startActivity(context, intent, null)
                        },
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

}
