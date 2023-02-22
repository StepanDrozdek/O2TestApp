package com.example.o2testapp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.o2testapp.navigation.Screens
import com.example.o2testapp.view.components.ScratchCard

@Composable
fun HomeScreen(navController: NavController) {
    Column(Modifier.fillMaxSize()) {
        ScratchCard()
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