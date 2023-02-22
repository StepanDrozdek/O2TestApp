package com.example.o2testapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.viewModel.components.ScratchCardViewModel

@Composable
fun ScratchCard() {
    val viewModel = ScratchCardViewModel()
    val scratchCard = viewModel.liveScratch.observeAsState().value

    Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .background(Color.Gray)
            .padding(10.dp)
    )
    {
        when (scratchCard?.state) {
            ScratchCardState.UNSCRATCHED -> {}
            ScratchCardState.SCRATCHED -> {
                Text(text = "${scratchCard.code}",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.White
                )
            }
            ScratchCardState.ACTIVE -> {
                Text(text = "ACTIVATED CODE ${scratchCard.code}",
                    modifier = Modifier.align(Alignment.CenterVertically),
                    color = Color.Green
                )
            }
        }
    }
}