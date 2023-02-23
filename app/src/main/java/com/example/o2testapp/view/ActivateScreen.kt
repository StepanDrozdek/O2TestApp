package com.example.o2testapp.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.o2testapp.constants.Global
import com.example.o2testapp.view.components.ScratchCard
import com.example.o2testapp.viewModel.ActivateScreenViewModel

@Composable
fun ActivateScreen() {
    val viewModel = ActivateScreenViewModel()
    val context = LocalContext.current
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
                    val data = Global.scratchCard.value?.copy()
                    if (data != null) {
                        viewModel.createRestApiAndCallO2(data, context)
                    }
                }) {
                Text(
                    text = "Activate"
                )
            }
        }
    }
}