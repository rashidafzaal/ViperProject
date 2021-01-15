package com.example.vipersample.view

import android.app.Activity
import android.os.Bundle
import com.example.vipersample.R
import com.example.vipersample.SplashImpl
import com.example.vipersample.presenter.SplashPresenter

class SplashActivity : Activity(), SplashImpl.View {

    private var presenter: SplashImpl.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter = SplashPresenter(this, this)
        presenter?.twoSecondsProgress()
    }

    override fun closeSplashActivity() {
        finish()
    }
}