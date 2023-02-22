package com.example.o2testapp.models

import com.example.o2testapp.constants.ScratchCardState

data class ScratchCardModel (
    var state: ScratchCardState = ScratchCardState.UNSCRATCHED,
    var code: String? = null
)