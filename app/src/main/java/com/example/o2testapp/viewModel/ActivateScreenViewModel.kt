package com.example.o2testapp.viewModel

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import com.example.o2testapp.MainActivity
import com.example.o2testapp.R
import com.example.o2testapp.constants.Global
import com.example.o2testapp.constants.ScratchCardState
import com.example.o2testapp.models.ScratchCardModel
import com.example.o2testapp.networking.RestApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ActivateScreenViewModel: ViewModel() {
    private val okHttpClient = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.o2.sk")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()



    fun createRestApiAndCallO2(scratchData: ScratchCardModel, context: Context){
        val restApi = retrofit.create(RestApi::class.java)
        callO2(scratchData = scratchData, context = context, restApi = restApi)
    }

    fun callO2(scratchData: ScratchCardModel, context: Context, restApi: RestApi) {
        scratchData.code?.let { code ->
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = restApi.getO2Response(code = code)
                    if (response.isSuccessful) {
                        val androidValue = response.body()?.getValue("android")?.toInt()
                        androidValue?.let {
                            if (it > 80000){
                                scratchData.state = ScratchCardState.ACTIVE
                                Global.scratchCard.postValue(scratchData)
                            } else {
                                sendNotification(context)
                            }
                        }
                    } else {
                        println(response.message())
                    }
                } catch (e: Exception){
                    println(e)
                }
            }
        }
    }

    private fun sendNotification(context: Context) {
        val mBuilder = NotificationCompat.Builder(context.applicationContext, "notify_001")
        val ii = Intent(context.applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, ii, 0)

        val bigText = NotificationCompat.BigTextStyle()
        bigText.bigText("Android under 80000")
        bigText.setBigContentTitle("Android under 80000")
        bigText.setSummaryText("Android under 80000")

        mBuilder.setContentIntent(pendingIntent)
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        mBuilder.setContentTitle("Android under 80000")
        mBuilder.setContentText("Android under 80000")
        mBuilder.priority = Notification.PRIORITY_MAX
        mBuilder.setStyle(bigText)

        val mNotificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = "Your_channel_id"
        val channel = NotificationChannel(
            channelId,
            "Android under 80000",
            NotificationManager.IMPORTANCE_HIGH
        )
        mNotificationManager.createNotificationChannel(channel)
        mBuilder.setChannelId(channelId)

        mNotificationManager.notify(0, mBuilder.build())
    }
}