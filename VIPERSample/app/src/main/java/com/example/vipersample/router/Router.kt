package com.example.vipersample.router

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import com.example.vipersample.Constants
import com.example.vipersample.R
import com.example.vipersample.entity.Movie
import com.example.vipersample.view.DetailActivity
import com.example.vipersample.view.MainActivity

class Router {

    companion object {
        fun newInstance(): Router = Router()
    }

    fun navigateToMain(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
        //animate
        (context as Activity).overridePendingTransition(R.anim.start_animation, R.anim.end_animation);
    }
    fun navigateToDetail(context: Context, movie: Movie?) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(Constants.KEY, movie)
        //animate
        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context as Activity).toBundle())
    }
}