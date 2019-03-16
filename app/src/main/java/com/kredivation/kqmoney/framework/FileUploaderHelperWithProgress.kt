package com.kredivation.kqmoney.framework

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.kredivation.kqmoney.utility.Contants
import okhttp3.*

import java.util.HashMap
import java.util.concurrent.TimeUnit

abstract class FileUploaderHelperWithProgress(
    internal var mContext: Context,
    internal var payload: HashMap<String, String>,
    internal var multipartBody: MultipartBody.Builder,
    internal var url: String
) : AsyncTask<String, Int, String>(), UploadLicenseImageCallback {
    internal var mProgressDialog: ProgressDialog=null!!

    override fun doInBackground(vararg params: String): String? {
        //String sourceImageFile = params[0];
        val responce = uploadImage(payload)
        return responce
    }
    override fun onPostExecute(result: String) {
        if (Contants.IS_DEBUG_LOG) {
            Log.d(Contants.LOG_TAG, "Save Data successfully****$result")
        }
        mProgressDialog.dismiss()
        receiveData(result)
    }

    override fun onPreExecute() {
        mProgressDialog = ProgressDialog(mContext)
        mProgressDialog.setMessage("Uploading...")
        mProgressDialog.isIndeterminate = false
        mProgressDialog.max = 100
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
    }

     override fun onProgressUpdate(vararg values: Int?) {
        mProgressDialog.progress = values[0] as Int
    }

    fun uploadImage(mapList: HashMap<String, String>): String? {
        try {

            for ((key, value) in mapList) {
                if (mapList != null) {
                    multipartBody.addFormDataPart(
                        key,
                        value
                    )//String.valueOf(entry.getValue()) for int value
                    Log.d(Contants.LOG_TAG, String.format("%s -> %s%n", key, value))
                }
            }
            val requestBody = multipartBody.build()
            val monitoredRequest = CountingRequestBody(requestBody, object : CountingRequestBody.Listener {
                override fun onRequestProgress(bytesWritten: Long, contentLength: Long) {
                    //Update a progress bar with the following percentage
                    val percentage = 100f * bytesWritten / contentLength
                    if (percentage >= 0) {
                        //TODO: Progress bar
                        publishProgress(Math.round(percentage))
                        Log.d("progress ", percentage.toString() + "")
                    } else {
                        //Something went wrong
                        Log.d("No progress ", 0.toString() + "")
                    }
                }
            })

            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + Contants.auth_token)
                .post(monitoredRequest)
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

