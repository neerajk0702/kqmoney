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

    private var profile_image: String? = null

    private var address: String? = null

    private var phone: String? = null

    private var total_coins: String? = null


    private var email: String? = null

    private var professional: String? = null

    fun getProfile_image(): String? {
        return profile_image
    }

    fun setProfile_image(profile_image: String) {
        this.profile_image = profile_image
    }

    fun getAddress(): String? {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getPhone(): String? {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
    }

    fun getTotal_coins(): String? {
        return total_coins
    }

    fun setTotal_coins(total_coins: String) {
        this.total_coins = total_coins
    }


    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getProfessional(): String? {
        return professional
    }

    fun setProfessional(professional: String) {
        this.professional = professional
    }

}