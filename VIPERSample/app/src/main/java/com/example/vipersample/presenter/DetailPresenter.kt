package com.example.vipersample.presenter

import android.view.View
import com.example.vipersample.DetailImpl
import com.example.vipersample.entity.Movie

class DetailPresenter: DetailImpl.Presenter {

    private var view: DetailImpl.View
    constructor(view: DetailImpl.View){
        this.view = view
    }

    override fun onViewCreated(movie: Movie) {
        view.setMovieData(movie)
    }
}