package com.digitalsamurai.kratorapp.network.udp.adapter

import java.net.DatagramPacket

interface UdpDataAdapter<T> {

    fun adaptUdpReceive(packet : DatagramPacket) : T
}