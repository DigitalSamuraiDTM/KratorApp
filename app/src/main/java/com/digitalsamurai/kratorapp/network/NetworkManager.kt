package com.digitalsamurai.kratorapp.network

import android.content.Context
import android.net.wifi.WifiManager
import android.text.format.Formatter
import java.net.InetAddress


class NetworkManager(private val context : Context) {

    private var wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

    /**
     * Deprecated method [Formatter.formatIpAddress] doesn't support IpV6
     * */
    fun getNetworkIpAddress() : String?{
        return Formatter.formatIpAddress(wifiManager.connectionInfo.ipAddress)
//        return InetAddress.getHostAddress()
    }
}