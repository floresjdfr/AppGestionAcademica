package com.example.gestionacademicaapp.ajax

import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.HttpURLConnection.HTTP_OK

/**
 * Class that helps to execute ajax requests
 * @param url
 * @param ajaxMethod
 * @param doInput (Not mandatory to specify) True if you want to receive a request body
 * @param doOutput (Not mandatory to specify) True if you want to send a request body
 * @param bodyJson
 * @param properties properties that can be added to the request
 */
class Ajax(
    private val url: String,
    private val ajaxMethod: AjaxMethod,
    private val doInput: Boolean? = null,
    private val doOutput: Boolean? = null,
    private val bodyJson: String = "",
    private val properties: ArrayList<Pair<String, String>> = ArrayList<Pair<String, String>>()
) : CoroutinesAsyncTask<Void, HttpResponse>("Ajax") {

    private fun httpRequest(): HttpResponse {
        val response = HttpResponse()
        var conn: HttpURLConnection? = null
        try {
            conn = Connect.connect(url)
            conn.requestMethod = ajaxMethod.toString()
            conn.doInput = setDoInput()
            conn.doOutput = setDoOutput()
            conn.setRequestProperty("Content-Type", "application/json")

            properties.forEach {
                conn.addRequestProperty(it.first, it.second)
            }

            if (conn.doOutput) {
                val outputStreamWriter = OutputStreamWriter(conn.outputStream)
                outputStreamWriter.write(bodyJson)
                outputStreamWriter.flush()
            }

            var responseCode = conn.responseCode

            var responseBody: String = if(responseCode == HTTP_OK)
                conn.inputStream.bufferedReader().use { it.readText() }
            else
                conn.errorStream.bufferedReader().use { it.readText() }

            response.responseCode = responseCode
            response.responseBody = responseBody

        } catch (ex: Exception) {
            response.responseCode = conn?.responseCode
            response.responseBody = ex.message
        }
        return response
    }

    private fun setDoInput(): Boolean {
        return doInput ?: true
    }

    private fun setDoOutput(): Boolean {
        return doOutput ?: (ajaxMethod == AjaxMethod.POST || ajaxMethod == AjaxMethod.PUT)
    }

    override fun doInBackground(): HttpResponse {
        return this.httpRequest()
    }

    override fun onPostExecute(result: HttpResponse?, postExecuteCallBack: (HttpResponse) -> Unit) {
        postExecuteCallBack.invoke(result!!)
    }
}

enum class AjaxMethod {
    GET, POST, PUT, DELETE
}