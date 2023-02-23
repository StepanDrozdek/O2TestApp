package com.example.o2testapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.o2testapp.constants.Global
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.models.ScratchCardModel
import kotlinx.coroutines.*
import java.util.*

class ScratchScreenViewModel: ViewModel() {
    fun scratch(): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val uuid = UUID.randomUUID()
            val data = ScratchCardModel(code = uuid.toString(), state = ScratchCardState.SCRATCHED )
            delay(2000)
            Global.scratchCard.postValue(data)
        }
    }
}