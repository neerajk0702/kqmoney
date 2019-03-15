package com.kredivation.kqmoney.framework

/**
 * Created by Neeraj on 7/25/2017.
 */
interface IServiceSuccessCallback {
    fun onDone(callerUrl: String, result: String, error: String)
}