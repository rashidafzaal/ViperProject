package com.example.vipersample.presenter

import android.content.Context
import android.util.Log
import com.android.volley.VolleyError
import com.example.vipersample.Constants
import com.example.vipersample.DatabaseHandler
import com.example.vipersample.MainImpl
import com.example.vipersample.R
import com.example.vipersample.entity.Movie
import com.example.vipersample.interactor.MainInteractor
import com.example.vipersample.router.Router
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainPresenter : MainImpl.Presenter {

    private var context: Context
    private var router: Router
    private var view: MainImpl.View
    private var interactor: MainImpl.Interactor? = MainInteractor()
    private var db: DatabaseHandler

    constructor(context: Context, view: MainImpl.View, db: DatabaseHandler) {
        this.context = context
        this.view = view
        router = Router.newInstance()
        this.db = db
    }

    override fun onListItemClicked(movie: Movie?) {
        router.navigateToDetail(context, movie)
    }

    override fun loadMoviesData(category: String) {
        view.showProgress()

        //load data from interactor
        interactor?.loadData(context, category, object : MainImpl.InteractorCallBacks {
            override fun onSuccess(response: JSONObject?) {
                view.hideProgress()

                val moviesList = arrayListOf<Movie>()
                moviesList.clear()

                //Parse Json Manually
                try {
                    val array: JSONArray? = response?.getJSONArray(Constants.RESULTS)
                    if (array != null) {
                        for (count in 0 until array.length()) {
                            val obj: JSONObject = (array.get(count) as JSONObject)
                            val movie = Movie()
                            movie.id = obj.getString(Constants.ID).toInt()
                            movie.title = obj.getString(Constants.TITLE).toString()
                            movie.description = obj.getString(Constants.DESCRIPTION).toString()
                            movie.rating = obj.getDouble(Constants.RATING)
                            movie.isAdult = obj.getBoolean(Constants.ADULT)
                            moviesList.add(movie)
                        }
                        view.publishList(moviesList)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

            override fun onFailed(error: VolleyError?) {
                view.hideProgress()

                // UnknownHostException = when internet is not available
                if (error?.message.toString().contains("UnknownHostException")) {
                    view.publishList(db.allMovies)
                } else {
                    view.showToast(context.resources.getString(R.string.something_went_wrong))
                }
            }
        })
    }


}