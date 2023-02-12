package com.digitalsamurai.kratorapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiDevice
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * How it works?
 * For begin u need start Instrumentation test from adb using: "adb shell"
* UDP broadcasting ->
 *
 */
@RunWith(AndroidJUnit4::class)
class Krator {
    private var scamFlow : SharedFlow<String>? = null

    private lateinit var device: UiDevice

    private lateinit var connection: ServiceConnection
    @Test
    fun useAppContext() = runBlocking{
        // Context of the app under test.



        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        connection = ScamConnection()
//        Log.d("TEST", "START")
//        println("START")
//                appContext.startForegroundService(Intent(appContext,KtorFetchService::class.java))
        appContext.bindService(Intent(appContext,KratorService::class.java),connection, Context.BIND_AUTO_CREATE)

        while (true){
            Log.d("KRATOR", "DELAY")
            delay(1000)
        }
        assertEquals("com.digitalsamurai.kratorapp", appContext.packageName)
    }
//    fun startCollection() = CoroutineScope(Dispatchers.Main).launch {
//        scamFlow?.collect{
//            val a = device.swipe(0,0,device.displayWidth,device.displayHeight,0)
//            val a = device.pressHome()
//            device.openNotification()

//            Log.d("TEST",a.toString())
//        }
//    }
    inner class ScamConnection : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as KratorService.ScamBinder
            scamFlow = binder.getScamState()
//            startCollection()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            scamFlow = null
        }
    }
}