package com.example.vipersample

import android.content.Context
import com.android.volley.VolleyError
import com.example.vipersample.entity.Movie
import org.json.JSONObject

interface MainImpl {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showToast(message: String)
        fun publishList(list: List<Movie>)
    }
    interface Presenter {
        fun onListItemClicked(movie: Movie?)
        fun loadMoviesData(category: String)
    }
    interface Interactor {
        fun loadData(context: Context, category: String, listener: InteractorCallBacks)
    }
    interface InteractorCallBacks {
        fun onSuccess(response: JSONObject?)
        fun onFailed(error: VolleyError?)
    }

}