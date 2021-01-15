package com.example.vipersample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vipersample.Constants
import com.example.vipersample.DetailImpl
import com.example.vipersample.R
import com.example.vipersample.entity.Movie
import com.example.vipersample.presenter.DetailPresenter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailImpl.View {

    private var detailPresenter: DetailImpl.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailPresenter = DetailPresenter(this)
        val argument = intent.getParcelableExtra<Movie>(Constants.KEY)
        argument?.let {
            detailPresenter?.onViewCreated(it)
        }
    }

    override fun setMovieData(movie: Movie) {
        tvTitle.text = movie.title
        tvDescription.text = movie.description
        tvRating.text = movie.rating.toString()
        tvAdult.text = if (movie.isAdult == true)
            resources.getString(R.string.eighteenPlus)
        else
            resources.getString(R.string.lessThanEighteen)
    }
}