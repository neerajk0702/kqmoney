package com.kredivation.kqmoney.framework

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication
import android.text.TextUtils
import android.view.MenuItem
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.Volley


/**
 * Created by Neeraj on 7/25/2017.
 */
class AppController : MultiDexApplication() {
    private var mRequestQueue: RequestQueue? = null

    private val _sTypeface: Typeface? = null
    @Volatile
    var lastMenuItem: MenuItem? = null
    private val sharedPref: SharedPreferences? = null
    private var packageNameStr: String? = null
    private val _fontRegular: Typeface? = null
    private val _fontBold: Typeface? = null
    private val _fontItalic: Typeface? = null
    private val _fontBoldItalic: Typeface? = null
    private val _fontSemiBold: Typeface? = null
    private val _fontSemiBoldItalic: Typeface? = null
    private val _fontLightItalic: Typeface? = null
    private val _fontExtraLightItalic: Typeface? = null

    val requestQueue: RequestQueue?
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return mRequestQueue
        }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        // set the default tag if tag is empty
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue?.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        requestQueue?.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    fun packageName(): String? {
        if (this.packageNameStr == null) {
            val info = this.packageInfo()
            this.packageNameStr = if (info != null) info.packageName else "com.kredivation.aakhale"
        }
        return this.packageNameStr
    }

    fun packageInfo(): PackageInfo? {
        val manager = this.packageManager
        var info: PackageInfo? = null
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0)
        } catch (e2: PackageManager.NameNotFoundException) {
            // FNExceptionUtil.logException(e2, getApplicationContext());
        }

        return info
    }

    companion object {
        val TAG = AppController::class.java
            .simpleName
        @get:Synchronized
        var instance: AppController? = null
            private set
    }

}
