package com.kredivation.kqmoney.model

class ContentData {

    private var status: Boolean = false

    private var result: Array<Result>? = null


    fun getResult(): Array<Result>? {
        return result
    }

    fun setResult(result: Array<Result>) {
        this.result = result
    }

    fun getStatus(): Boolean {
        return status
    }

    fun setStatus(status: Boolean) {
        this.status = status
    }


}