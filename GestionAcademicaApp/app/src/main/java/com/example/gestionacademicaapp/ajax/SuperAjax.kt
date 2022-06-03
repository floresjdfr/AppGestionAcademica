package com.example.gestionacademicaapp.ajax

import com.example.gestionacademicaapp.models.Base
import com.example.gestionacademicaapp.models.HttpResponse
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import com.example.gestionacademicaapp.models.Error


/**
 * Class that helps to execute ajax requests
 * @param url
 * @param ajaxMethod
 * @param doInput (Not mandatory to specify) True if you want to receive a request body
 * @param doOutput (Not mandatory to specify) True if you want to send a request body
 * @param bodyJson
 * @param properties properties that can be added to the request
 */
class SuperAjax(
    private val url: String,
    private val ajaxMethod: AjaxMethod,
    private val doInput: Boolean? = null,
    private val doOutput: Boolean? = null,
    private val bodyJson: String = "",
    private val properties: ArrayList<Pair<String, String>> = ArrayList<Pair<String, String>>()
){

    private suspend fun httpRequest(): Base {
        var response = Base()
        var conn: HttpURLConnection? = null
        try {
            conn = Connect.connect(url)
            conn.requestMethod = ajaxMethod.toString()
            conn.doInput = setDoInput()
            conn.doOutput = setDoOutput()

            properties.forEach {
                conn.addRequestProperty(it.first, it.second)
            }

            if (conn.doOutput) {
                val outputStreamWriter = OutputStreamWriter(conn.outputStream)
                outputStreamWriter.write(bodyJson)
                outputStreamWriter.flush()
            }

            var responseCode = conn.responseCode

            var responseBody: String = if(responseCode == HttpURLConnection.HTTP_OK)
                conn.inputStream.bufferedReader().use { it.readText() }
            else
                conn.errorStream.bufferedReader().use { it.readText() }

            response = HttpResponse()
            response.responseCode = responseCode
            response.responseBody = responseBody

        } catch (ex: Exception) {
            response = Error()
            response.ErrorMessage = ex.message
        }
        return response
    }

    private fun setDoInput(): Boolean {
        return doInput ?: true
    }

    private fun setDoOutput(): Boolean {
        return doOutput ?: (ajaxMethod == AjaxMethod.POST || ajaxMethod == AjaxMethod.PUT)
    }

    suspend fun execute(): Base {
        return this.httpRequest()
    }
}