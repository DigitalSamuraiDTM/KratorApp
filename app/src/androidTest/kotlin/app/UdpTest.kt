package app

import android.util.Log
import com.digitalsamurai.kratorapp.network.udp.UdpBroadcastReceiver
import com.digitalsamurai.kratorapp.network.udp.adapter.UdpDataAdapterKratorServer
import com.digitalsamurai.kratorapp.network.udp.entity.UdpDataKratorServer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.junit.Before
import org.junit.Test

class UdpTest {

    lateinit var udpBroadcastReceiver: UdpBroadcastReceiver<UdpDataKratorServer>

    @Before
    fun before(){
        val adapter = UdpDataAdapterKratorServer()
        udpBroadcastReceiver = UdpBroadcastReceiver(false,adapter)
    }

    @Test
    fun test(): Unit = runBlocking {


        Log.d("UDP_TEST","ACTIVE PORT: " + udpBroadcastReceiver.getPort().toString())
        var countD = 1
        var count = 1
        CoroutineScope(Dispatchers.IO).launch {
            while (true){
                Log.d("UDP_TEST","OPA POPA ${countD}")
                countD++
                delay(1000)
            }
        }
        udpBroadcastReceiver.startReceiving().collect{
            Log.d("UDP_TEST","${it} ${count}")
            count++
        }
        assert(true)
    }
}