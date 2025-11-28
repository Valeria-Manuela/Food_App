package com.example.food_app.Activity.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TopBar(){
    Row(
        modifier = Modifier
            .padding(top = 48.dp)
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image (
            painter = painterResource(R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(40.dp)
                .clickable {}
        )

        var text by rememberSaveable { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            label = {
                Text(
                    text = "O que você gostaria de comer?",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray
                )
            },
            trailingIcon = {
                Image(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "Search Icon",
                    modifier = Modifier.size(20.dp)
                )
            },
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp)
                .height(45.dp),

            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorResource(R.color.lightGrey),
                unfocusedContainerColor = colorResource(R.color.lightGrey),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedTextColor = Color.DarkGray,
                unfocusedTextColor = Color.DarkGray,
                unfocusedLabelColor = Color.DarkGray,
                focusedLabelColor = Color.DarkGray
            )
        )

        Image(
            painter = painterResource(R.drawable.bell_icon),
            contentDescription = "Notifications",
            modifier = Modifier
                .size(40.dp)
                .clickable{}
        )
    }
}