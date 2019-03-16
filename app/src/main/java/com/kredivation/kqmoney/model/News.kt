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
}
