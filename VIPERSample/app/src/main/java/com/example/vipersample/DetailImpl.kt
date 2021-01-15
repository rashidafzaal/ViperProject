package com.example.vipersample

import com.example.vipersample.entity.Movie

interface DetailImpl {
    interface View {
        fun setMovieData(movie: Movie)
    }

    interface Presenter {
        fun onViewCreated(movie: Movie)
    }
}