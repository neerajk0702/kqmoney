package com.kredivation.kqmoney.framework

import org.json.JSONObject

/**
 * Created by Neeraj on 7/25/2017.
 */
interface IServiceHelper {
    //--this method runs an AsyncTask and calls supplied Callback object's methods when done
    fun callService(url: String, jsonInputObj: JSONObject, c: IServiceSuccessCallback)

    //--this method just processes input string locally and returns it
    fun doStuff(input: String): String

    fun callGetService(url: String, jsonInputObj: JSONObject, c: IServiceSuccessCallback)

    fun callJsonArryService(url: String, params: Map<String, String>, c: IServiceSuccessCallback)
}

