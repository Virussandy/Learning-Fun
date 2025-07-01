package com.example.example2.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomePage(themeValue: MutableState<Boolean>) {

    Column (
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
            .padding(8.dp, 24.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "HomePage", style =  MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.fillMaxWidth(.1f))
            Switch(
                    checked = themeValue.value,
                    onCheckedChange = { themeValue.value = it },
                    modifier = Modifier
                )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card (){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp,8.dp)
            ) {
                Text(text = "Sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

                Text(text = "Hello Everyone my name is sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

            }

        }
        Spacer(modifier = Modifier.height(16.dp))

        Card (){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp,8.dp)
            ) {
                Text(text = "Sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

                Text(text = "Hello Everyone my name is sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Card (){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp,8.dp)
            ) {
                Text(text = "Sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

                Text(text = "Hello Everyone my name is sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Card (){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp,8.dp)
            ) {
                Text(text = "Sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

                Text(text = "Hello Everyone my name is sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Card (){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp,8.dp)
            ) {
                Text(text = "Sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

                Text(text = "Hello Everyone my name is sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Card (){
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp,8.dp)
            ) {
                Text(text = "Sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

                Text(text = "Hello Everyone my name is sandeep",modifier = Modifier.padding(0.dp,5.dp)
                    .fillMaxWidth())

            }

        }
    }

}