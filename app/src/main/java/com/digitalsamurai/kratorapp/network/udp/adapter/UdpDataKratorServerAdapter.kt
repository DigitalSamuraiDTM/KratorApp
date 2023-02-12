package com.digitalsamurai.kratorapp.network.udp.adapter

import com.digitalsamurai.kratorapp.network.udp.entity.UdpDataKratorServer
import java.net.DatagramPacket

class UdpDataAdapterKratorServer : UdpDataAdapter<UdpDataKratorServer> {
    override fun adaptUdpReceive(packet: DatagramPacket): UdpDataKratorServer {
        return UdpDataKratorServer(
            hostIp = packet.address.hostName,
            hostPort = packet.port,
            message = String(packet.data))
    }
}