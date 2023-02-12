package com.digitalsamurai.kratorapp

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KtorServer {

    fun init(){
        embeddedServer(Netty, port = 7070, host = "192.168.31.12") {
                routing {
                    get("/get") {
                        call.respond("OPA POPA")
                    }
                }
        }.start(false)


    }
}