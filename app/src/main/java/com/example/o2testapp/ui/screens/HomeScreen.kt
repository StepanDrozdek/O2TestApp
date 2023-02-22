package com.example.o2testapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.o2testapp.navigation.Screens

@Composable
fun HomeScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Hello Home!")
        Row(Modifier.fillMaxSize()) {
            Button(onClick = {
                navController.navigate(Screens.Scratch.route)
            }) {
                Text(text = "Navigate Scratch")
            }
            Button(onClick = {
                navController.navigate(Screens.Activate.route)
            }) {
                Text(text = "Navigate Activate")
            }
        }
    }
}