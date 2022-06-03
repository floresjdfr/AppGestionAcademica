package com.example.gestionacademicaapp.ajax

import java.net.HttpURLConnection
import java.net.URL

internal class Connect {
    companion object {
        fun connect(url: String): HttpURLConnection {
            val url = URL(url)
            val conn = url.openConnection() as HttpURLConnection

            //PROPS
            conn.connectTimeout = 5000
            conn.readTimeout = 5000

            return conn;
        }
    }
}