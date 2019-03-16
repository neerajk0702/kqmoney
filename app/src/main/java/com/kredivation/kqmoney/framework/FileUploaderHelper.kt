package com.kredivation.kqmoney.framework

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.kredivation.kqmoney.utility.Contants
import okhttp3.*

import java.util.HashMap
import java.util.Map
import java.util.concurrent.TimeUnit

/**
 * Created by Neeraj on 3/22/2017.
 */

abstract class FileUploaderHelper(
    internal var mContext: Context,
    internal var payload: HashMap<String, String>,
    internal var multipartBody: MultipartBody.Builder,
    internal var url: String
) : AsyncTask<String, Int, String>(), UploadLicenseImageCallback {

    override fun doInBackground(vararg params: String): String? {
        //String sourceImageFile = params[0];
        val responce = uploadImage(payload)
        return responce
    }

    override fun onPostExecute(result: String) {
        if (Contants.IS_DEBUG_LOG) {
            Log.d(Contants.LOG_TAG, "image uploaded successfully****$result")
        }
        receiveData(result)
    }

    override fun onPreExecute() {}

    override fun onProgressUpdate(vararg values: Int?) {}

    fun uploadImage(mapList: HashMap<String, String>): String? {
        try {

            for ((key, value) in mapList) {
                if (mapList.values != null) {
                    multipartBody.addFormDataPart(key, value)//String.valueOf(entry.getValue()) for int value
                    Log.d(Contants.LOG_TAG, String.format("%s -> %s%n", key, value))
                }
            }
            val requestBody = multipartBody.build()

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val b = OkHttpClient.Builder()
            b.connectTimeout(180, TimeUnit.SECONDS)
            b.writeTimeout(180, TimeUnit.SECONDS)
            b.readTimeout(180, TimeUnit.SECONDS)
            val client = b.build()

            val response = client.newCall(request).execute()
            return response.body()!!.string()

        } catch (e: Exception) {
            Log.e(Contants.LOG_TAG, "Error: " + e.localizedMessage)
        }

        return null
    }

    abstract override fun receiveData(result: String?)


}
