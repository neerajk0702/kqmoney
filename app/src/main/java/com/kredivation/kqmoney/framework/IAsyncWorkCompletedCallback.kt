package com.kredivation.kqmoney.framework

/**
 * Created by Neeraj on 7/25/2017.
 */
interface IAsyncWorkCompletedCallback {
    fun onDone(workName: String?, isComplete: Boolean)
}
