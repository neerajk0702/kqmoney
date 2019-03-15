package com.kredivation.kqmoney.framework

import android.util.Log
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.kredivation.kqmoney.utility.Contants
import org.json.JSONArray
import org.json.JSONObject

import java.util.HashMap


/**
 * Created by Neeraj on 7/25/2017.
 */
class ServiceHelper : IServiceHelper {

    override fun callService(url: String, jsonInputObj: JSONObject, c: IServiceSuccessCallback) {
        val callerUrl = url
        val jsonObjReq = object : JsonObjectRequest(Request.Method.POST,
            url, jsonInputObj,
            Response.Listener { response ->
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "successfully called $callerUrl")
                }
                c.onDone(callerUrl, response.toString(), null!!)
            }, Response.ErrorListener { error ->
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "Error at ServiceHelper: callService " + callerUrl + ": " + error.message)
                }
                c.onDone(callerUrl, null!!, error.message!!)
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }
        }
        // Adding request to request queue
        jsonObjReq.retryPolicy = DefaultRetryPolicy(30 * 1000, 0, 1.0f)
        AppController.instance?.addToRequestQueue(jsonObjReq)//, tag_json_obj);
        //AppController.getInstance().cancelPendingRequests(jsonObjReq);
    }

    override fun callJsonArryService(url: String, params: Map<String, String>, c: IServiceSuccessCallback) {
        val callerUrl = url
        val jsonarray = object : JsonArrayRequest(url,
            Response.Listener { response ->
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "successfully called $callerUrl")
                }
                c.onDone(callerUrl, response.toString(), null!!)
            }, Response.ErrorListener { error ->
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "Error at ServiceHelper: callService " + callerUrl + ": " + error.message)
                }
                c.onDone(callerUrl, null!!, error.message!!)
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                /* Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "login");
                params.put("email", emailName);
                params.put("password", passwordName);*/
                return params
            }
        }
        // Adding request to request queue
        jsonarray.retryPolicy = DefaultRetryPolicy(10 * 1000, 0, 1.0f)
        AppController.instance?.addToRequestQueue(jsonarray)//, tag_json_obj);

        //AppController.getInstance().cancelPendingRequests(jsonObjReq);
    }

    override fun doStuff(input: String): String {
        return null!!
    }

    override fun callGetService(url: String, jsonInputObj: JSONObject, c: IServiceSuccessCallback) {
        val callerUrl = url
        val jsonObjReq = object : JsonObjectRequest(Request.Method.GET,
            url, null,
            Response.Listener { response ->
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "successfully called $callerUrl")
                }
                c.onDone(callerUrl, response.toString(), null!!)
            }, Response.ErrorListener { error ->
                if (Contants.IS_DEBUG_LOG) {
                    Log.d(Contants.LOG_TAG, "Error at ServiceHelper: callService " + callerUrl + ": " + error.message)
                }
                c.onDone(callerUrl, null!!, error.message!!)
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json; charset=utf-8"
                return headers
            }
        }
        // Adding request to request queue
        jsonObjReq.retryPolicy = DefaultRetryPolicy(30 * 1000, 0, 1.0f)
        AppController.instance?.addToRequestQueue(jsonObjReq)//, tag_json_obj);
        //AppController.getInstance().cancelPendingRequests(jsonObjReq);
    }
}
