package com.digitalsamurai.kratorapp.network.udp.entity

data class UdpDataKratorServer (
    val hostIp : String,
    val hostPort : Int,
    val message : String
        )