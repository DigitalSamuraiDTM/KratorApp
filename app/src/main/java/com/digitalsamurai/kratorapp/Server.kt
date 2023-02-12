package com.digitalsamurai.kratorapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow


class KratorService : Service() {

    var scamBinder = ScamBinder()
    override fun onBind(intent: Intent?): IBinder {
        return scamBinder
    }

    private lateinit var uiDevice: UiDevice
    private val HTTP_PORT = 7070

    override fun onCreate() {



        val instrument = InstrumentationRegistry.getInstrumentation()
        uiDevice = UiDevice.getInstance(instrument)



        val uiAutomation = instrument.uiAutomation

        val id = 112312312
        val name = id.toString()
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(id.toString(),name, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }
        startForeground(id, NotificationCompat.Builder(this,id.toString())
            .setContentTitle("SCAM SERVER ACTIVED")
            .setContentText("AT 192.168.31.12").build())


        super.onCreate()
    }





    private val scamFlow = MutableSharedFlow<String>()
    inner class ScamBinder() : Binder(){
        fun getScamState() : SharedFlow<String>{
            return scamFlow
        }
    }

}