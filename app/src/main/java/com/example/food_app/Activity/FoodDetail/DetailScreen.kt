package com.example.food_app.Activity.FoodDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.food_app.Activity.Dashboard.DescriptionSection
import com.example.food_app.Activity.Dashboard.FooterSection
import com.example.food_app.Activity.Dashboard.RecommendedList
import com.example.food_app.Domain.FoodModel
import com.example.food_app.R
import com.example.food_app.ViewModel.MainViewModel

@Composable
fun DetailScreen(
    item: FoodModel,
    onBackClick: () -> Unit,
    onAddToCartClick: () -> Unit
) {
    val mainViewModel: MainViewModel = viewModel()

    var numberinCart by remember { mutableIntStateOf(item.numberInCart) }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (column,footer) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.lightGrey))
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
                .constrainAs(column) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            HeaderSection(item = item, onBackClick = onBackClick)

            TitleNumberRow(
                item = item,
                numberInCart = numberinCart,
                onIncrement = {
                    numberinCart++
                    item.numberInCart = numberinCart
                },
                onDecrement = {
                    if (numberinCart > 1) {
                        numberinCart--
                        item.numberInCart = numberinCart
                    }
                }
            )

            Text(
                text = "$${item.Price}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.black),
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            RowDetail(item)
            DescriptionSection(item.Description)

            RecommendedList(viewModel = mainViewModel)
        }
        FooterSection(
            onAddToCartClick,
            totalPrice = (item.Price*numberinCart),
            Modifier.constrainAs(footer){
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
        )
    }

}
