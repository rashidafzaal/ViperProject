package com.example.vipersample

interface SplashImpl {

    interface View {
        fun closeSplashActivity()
    }
    interface Presenter {
        fun twoSecondsProgress()
    }
}