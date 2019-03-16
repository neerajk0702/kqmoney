package com.kredivation.kqmoney.model

/**
 * Created by vamsitallapudi on 16/01/18.
 */

import java.util.Date

class News {


    /**
     * id : 1
     * news_title : Hello Vamsi, This is your First News Title!
     * news_detail : Hey Vamsi, this is our first News Detail. So exciting it is, learning python, sql and writing First news App backend in Django. Its thrilling and lets continue our best effort!
     * news_image_url : http://quoteshunter.com/wp-content/uploads/2016/02/Never-Give-Up-Quotes-6.jpg
     * news_url : http://coderefer.com/
     * pub_date : 2017-07-18T18:31:50Z
     */

    var id: Int = 0
    var news_title: String? = null
    var news_detail: String? = null
    var news_image_url: String? = null
    var news_url: String? = null
    var news_source: String? = null
    var pub_date: Date? = null


    var name: String? = null
    var qname: String? = null
    var image_drawable: Int = 0

    constructor()

    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }

    fun getImage_drawables(): Int {
        return image_drawable
    }

    fun setImage_drawables(image_drawable: Int) {
        this.image_drawable = image_drawable
    }

    fun getqname(): String {
        return qname.toString()
    }

    fun setqname(name: String) {
        this.qname = name
    }


    constructor(name: String, image: Int) {
        this.name = name
        this.image_drawable = image
    }


    private var verifyUserId: String? = null

    private var correct_coins: String? = null

    private var wrong_coins: String? = null

    private var ans_json: String? = null

    private var description: String? = null

    private var title: String? = null

    private var userId: String? = null

    private var total_coins: String? = null

    private var comment: String? = null


    private var attempted: String? = null

    private var createdatetime: String? = null

    private var categoryId: String? = null

    private var status: String? = null

    fun getVerifyUserId(): String? {
        return verifyUserId
    }

    fun setVerifyUserId(verifyUserId: String) {
        this.verifyUserId = verifyUserId
    }

    fun getCorrect_coins(): String? {
        return correct_coins
    }

    fun setCorrect_coins(correct_coins: String) {
        this.correct_coins = correct_coins
    }

    fun getWrong_coins(): String? {
        return wrong_coins
    }

    fun setWrong_coins(wrong_coins: String) {
        this.wrong_coins = wrong_coins
    }

    fun getAns_json(): String? {
        return ans_json
    }

    fun setAns_json(ans_json: String) {
        this.ans_json = ans_json
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun getTotal_coins(): String? {
        return total_coins
    }

    fun setTotal_coins(total_coins: String) {
        this.total_coins = total_coins
    }

    fun getComment(): String? {
        return comment
    }

    fun setComment(comment: String) {
        this.comment = comment
    }


    fun getAttempted(): String? {
        return attempted
    }

    fun setAttempted(attempted: String) {
        this.attempted = attempted
    }

    fun getCreatedatetime(): String? {
        return createdatetime
    }

    fun setCreatedatetime(createdatetime: String) {
        this.createdatetime = createdatetime
    }

    fun getCategoryId(): String? {
        return categoryId
    }

    fun setCategoryId(categoryId: String) {
        this.categoryId = categoryId
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String) {
        this.status = status
    }
}
