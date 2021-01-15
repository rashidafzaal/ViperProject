package com.example.vipersample.presenter

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.vipersample.router.Router
import com.example.vipersample.SplashImpl

class SplashPresenter: SplashImpl.Presenter {

    private val SPLASH_TIME: Long = 2000
    private var context: Context
    private var router: Router
    private var view: SplashImpl.View

    constructor (context: Context, view: SplashImpl.View) {
        this.context = context
        this.view = view
        router = Router.newInstance()
    }
    override fun twoSecondsProgress() {
        Handler(Looper.getMainLooper()).postDelayed({
            //delay code by 2 seconds
            router.navigateToMain(context)
            view.closeSplashActivity()
        }, SPLASH_TIME)
    }
}