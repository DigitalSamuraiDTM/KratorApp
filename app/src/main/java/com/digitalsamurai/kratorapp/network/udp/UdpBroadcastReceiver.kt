package com.digitalsamurai.kratorapp.network.udp

import android.util.Log
import com.digitalsamurai.kratorapp.network.udp.adapter.UdpDataAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetSocketAddress

//todo check internet access and restart when need

class UdpBroadcastReceiver<T>(private val autoStart : Boolean = false, private val udpDataAdapter : UdpDataAdapter<T>) {


    private var udpSocket = DatagramSocket(InetSocketAddress("192.168.31.255",8888))

    private var buffer = ByteArray(5)
    private var udpPacket = DatagramPacket(buffer,buffer.size)

    private var isActive = true

    private var _udpReceiveFlow = MutableSharedFlow<T>()

    private var udpJob : Job? = null
    private var udpScope = CoroutineScope(Dispatchers.IO)
    init {
        if (autoStart){
            startReceiving()
        }
    }

    fun getPort() : Int = udpSocket.port

    fun startReceiving() : SharedFlow<T>{
        isActive = true
        if (!(udpJob!=null && udpJob?.isActive == true)){
            receive()
        }
        return _udpReceiveFlow
    }

    fun stopReceiving(){
        udpJob?.cancel()
        isActive = false
    }

    private fun receive(){
        udpJob?.cancel()
        udpJob = udpScope.launch {
            while (isActive) {
                try {
                    udpSocket.receive(udpPacket)
                    _udpReceiveFlow.emit(udpDataAdapter.adaptUdpReceive(udpPacket))
                } catch (e : java.lang.Exception){
                    Log.d("UDP_TEST", "OPA POPA: ${e.message}")
                }
            }
        }
    }


    companion object{
        const val DEFAULT_PORT = 161
    }
}