package com.kredivation.kqmoney.framework

import android.content.Context
import android.util.Log
import com.kredivation.kqmoney.utility.Contants
import org.json.JSONObject

/**
 * Created by Neeraj on 7/25/2017.
 */
class ServiceCaller(internal var context: Context) {

    //call Commen Method ForCall Servier Data
    fun CallCommanServiceMethod(
        loginUrl: String,
        methodNmae: String,
        workCompletedCallback: IAsyncWorkCompletedCallback
    ) {
        ServiceHelper().callService(loginUrl, null!!, object : IServiceSuccessCallback {
            override fun onDone(doneWhatCode: String, result: String, error: String) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true)
                    Log.d(Contants.LOG_TAG, "$methodNmae********$result")
                } else {
                    workCompletedCallback.onDone(methodNmae, false)
                }
            }
        })
    }

    //call Commen Method ForCall Servier Data with json Object
    fun CallCommanServiceMethod(
        loginUrl: String,
        jsonObject: JSONObject,
        methodNmae: String,
        workCompletedCallback: IAsyncWorkCompletedCallback
    ) {
        Log.d(Contants.LOG_TAG, methodNmae + "Payload********" + jsonObject.toString())
        ServiceHelper().callService(loginUrl, jsonObject, object : IServiceSuccessCallback {
            override fun onDone(doneWhatCode: String, result: String, error: String) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true)
                    Log.d(Contants.LOG_TAG, "$methodNmae********$result")
                } else {
                    workCompletedCallback.onDone(methodNmae, false)
                }
            }
        })
    }

    //call Commen Method ForCall Servier Data with json Object
    fun CallCommanGetServiceMethod(
        loginUrl: String,
        jsonObject: JSONObject,
        methodNmae: String,
        workCompletedCallback: IAsyncWorkCompletedCallback
    ) {
        Log.d(Contants.LOG_TAG, methodNmae + "Payload********" + jsonObject.toString())
        ServiceHelper().callGetService(loginUrl, jsonObject, object : IServiceSuccessCallback {
            override fun onDone(doneWhatCode: String, result: String, error: String) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true)
                    Log.d(Contants.LOG_TAG, "$methodNmae********$result")
                } else {
                    workCompletedCallback.onDone(methodNmae, false)
                }
            }
        })
    }


    //call Commen Method ForCall Servier Data
    fun CallCommanGetServiceMethod(
        loginUrl: String,
        methodNmae: String,
        workCompletedCallback: IAsyncWorkCompletedCallback
    ) {
        ServiceHelper().callGetService(loginUrl, null!!, object : IServiceSuccessCallback {
            override fun onDone(doneWhatCode: String, result: String, error: String) {
                if (result != null) {
                    workCompletedCallback.onDone(result, true)
                    Log.d(Contants.LOG_TAG, "$methodNmae********$result")
                } else {
                    workCompletedCallback.onDone(methodNmae, false)
                }
            }
        })
    }

}
