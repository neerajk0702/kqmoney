package com.kredivation.allquestionanswer.model

import java.util.*

data class AllData(
        val id:Int=0,
        val news_title:String?=null,
        var news_detail: String? = null,
        var news_image_url: String? = null,
        var news_url: String? = null,
        var news_source: String? = null,
        var pub_date: Date? = null
    )