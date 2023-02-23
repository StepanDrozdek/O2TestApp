package com.example.o2testapp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.o2testapp.constants.Global
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.models.ScratchCardModel
import com.example.o2testapp.networking.RestApi
import com.example.o2testapp.viewModel.ActivateScreenViewModel
import com.google.gson.internal.LinkedTreeMap
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import retrofit2.Response
import java.util.*

class ActiveScreenUnitTest {
    private val activateScreenViewModel = ActivateScreenViewModel()
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `send valid request`(){
        val restApi = Mockito.mock(RestApi::class.java)
        val mockContext = Mockito.mock(Context::class.java)
        val data = ScratchCardModel(code = UUID.randomUUID().toString(), state = ScratchCardState.SCRATCHED )

        val responseData: LinkedTreeMap<String, String> = LinkedTreeMap()
        responseData["android"] = "92275"
        val response = Response.success(200, responseData)

        Global.scratchCard.postValue(data)
        runBlocking {
            data.code?.let { itCode ->
                `when`(restApi.getO2Response(itCode)).thenReturn(response)
                activateScreenViewModel.callO2(scratchData = data, context =  mockContext, restApi = restApi)
            }
            delay(2000)
        }

        val expectedData = data.copy(state = ScratchCardState.ACTIVE)
        Assert.assertEquals(expectedData, Global.scratchCard.value)
    }

    @Test
    fun `get response lower then 80000`(){
        val restApi = Mockito.mock(RestApi::class.java)
        val mockContext = Mockito.mock(Context::class.java)
        val data = ScratchCardModel(code = UUID.randomUUID().toString(), state = ScratchCardState.SCRATCHED )

        val responseData: LinkedTreeMap<String, String> = LinkedTreeMap()
        responseData["android"] = "66666"
        val response = Response.success(200, responseData)

        Global.scratchCard.postValue(data)
        runBlocking {
            data.code?.let { itCode ->
                `when`(restApi.getO2Response(itCode)).thenReturn(response)
                activateScreenViewModel.callO2(scratchData = data, context =  mockContext, restApi = restApi)
            }
            delay(2000)
        }

        Assert.assertEquals(data, Global.scratchCard.value)
    }


    @Test
    fun `code is null`(){
        val restApi = Mockito.mock(RestApi::class.java)
        val mockContext = Mockito.mock(Context::class.java)
        val data = ScratchCardModel(code = null, state = ScratchCardState.SCRATCHED )

        val responseData: LinkedTreeMap<String, String> = LinkedTreeMap()
        responseData["android"] = "66666"
        val response = Response.success(200, responseData)

        Global.scratchCard.postValue(data)
        runBlocking {
            data.code?.let { itCode ->
                `when`(restApi.getO2Response(itCode)).thenReturn(response)
                activateScreenViewModel.callO2(scratchData = data, context =  mockContext, restApi = restApi)
            }
            delay(2000)
        }

        Assert.assertEquals(Global.scratchCard.value?.code, null)
    }
}
