package com.example.o2testapp.view.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.viewModel.components.ScratchCardViewModel

@Composable
fun ScratchCard() {
    val viewModel = ScratchCardViewModel()
    val scratchCard = viewModel.liveScratch.observeAsState().value
    when (scratchCard?.state) {
        ScratchCardState.UNSCRATCHED -> { Text(text = "UNSCRATCHED") }
        ScratchCardState.SCRATCHED -> { Text(text = "SCRATCHED") }
        ScratchCardState.ACTIVE -> { Text(text = "ACTIVE") }
    }
}