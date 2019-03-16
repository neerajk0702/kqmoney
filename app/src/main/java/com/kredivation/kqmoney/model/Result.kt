package com.kredivation.kqmoney.model

class Result {
    private var response: String? = null

    fun getResponse(): String? {
        return response
    }

    fun setResponse(response: String) {
        this.response = response
    }

    override fun toString(): String {
        return "ClassPojo [response = $response]"
    }


    private var name: String? = null

    private var id: String? = null

    private var status: String? = null

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }

}