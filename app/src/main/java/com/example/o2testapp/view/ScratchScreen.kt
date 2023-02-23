package com.example.o2testapp.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.o2testapp.constants.Global
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.view.shared.ScratchCard
import com.example.o2testapp.viewModel.ScratchScreenViewModel
import kotlinx.coroutines.*

@Composable
fun ScratchScreen(navController: NavController) {
    val viewModel = ScratchScreenViewModel()
    var job: Job? = null
    Column(Modifier.fillMaxSize()) {
        ScratchCard()
        Row(modifier = Modifier
            .padding(top = 20.dp)
            .align(Alignment.CenterHorizontally)
        ) {
            Button(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.2f),
                onClick = {
                    if (job == null){
                        job = viewModel.scratch()
                    }
                }) {
                Text(
                    text = "Scratch"
                )
            }
        }
    }

    BackHandler {
        if (Global.scratchCard.value?.state == ScratchCardState.UNSCRATCHED && job != null){
            job?.cancel()
        }
        navController.navigateUp()
    }
}