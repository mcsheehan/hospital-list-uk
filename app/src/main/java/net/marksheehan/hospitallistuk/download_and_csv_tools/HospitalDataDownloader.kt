package net.marksheehan.hospitallistuk.download_and_csv_tools

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.InputStream

class HospitalDataDownloader {
    @RequiresApi(Build.VERSION_CODES.N)
    fun downloadUrl(downloadUrl: String): String {
        // This request and response should be handled with a proper error callback
        val client = OkHttpClient()
        val request = Request.Builder().url(downloadUrl).build()
        val response = client.newCall(request).execute()

        //Connection failure should really be handled here. There are so many reasons this http request could fail (but time constraints)
        //response.isSuccessful
        val inputStream = response.body()?.byteStream()

        val inputAsString = inputStream?.bufferedReader().use { it?.readText() }  // defaults to UTF-8
        response.body()?.close()

        if (inputAsString == null){
            return ""
        }

        return inputAsString
    }

    fun saveInputStreamToFile(inputStream: InputStream, saveFilePath: String) {
        val myfile = File(saveFilePath)
        val outputStream = myfile.outputStream()
        inputStream.copyTo(outputStream)
        outputStream.flush()
    }

    fun inputStreamToString() {

    }
}