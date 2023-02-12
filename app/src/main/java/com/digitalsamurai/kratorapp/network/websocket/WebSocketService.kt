package com.digitalsamurai.kratorapp.network.websocket

import com.digitalsamurai.kratorapp.network.websocket.entity.WebSocketState
import com.digitalsamurai.kratorapp.network.websocket.entity.WebsocketSendMessage
import com.google.gson.Gson

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import okhttp3.*
import okio.ByteString


class WebSocketService(private val gson : Gson) {

    private var okHttpClient : OkHttpClient = OkHttpClient()
    private var socketListener : OsmiumWebSocketListener
    private var webSocketState : MutableSharedFlow<WebSocketState> = MutableSharedFlow(1)


    private var ws : WebSocket?  = null


    companion object{

    }

    init {
        socketListener = OsmiumWebSocketListener()
    }
    fun initSocketConnection() : SharedFlow<WebSocketState> {
        //if our socket is null we can create new socket connection and send hello message in Socket Listener
        if (ws == null) {
            ws = okHttpClient.newWebSocket(
                Request.Builder().url("TODO").build(),
                socketListener
            )
            okHttpClient.dispatcher().executorService().shutdown()
        } else{
            //recreate shared flow, close previous socket connection if need -> open new connection
            webSocketState = MutableSharedFlow()
            ws?.close(1000,"That's all")
        }
        return webSocketState
    }

    fun closeWebsocket() : Boolean = ws?.close(1000,"That's all") ?: false

    fun sendMessage(message : WebsocketSendMessage) : Boolean = ws?.send(gson.toJson(message)) ?: false

    private inner class OsmiumWebSocketListener : WebSocketListener(){
        override fun onOpen(webSocket: WebSocket, response: Response) {

            super.onOpen(webSocket, response)
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
        }
    }

}