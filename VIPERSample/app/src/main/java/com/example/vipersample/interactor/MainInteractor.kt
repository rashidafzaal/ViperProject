package com.example.vipersample.interactor

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.vipersample.Constants
import com.example.vipersample.MainImpl
import org.json.JSONObject


class MainInteractor: MainImpl.Interactor {

    override fun loadData(context: Context, category: String, listener: MainImpl.InteractorCallBacks) {
        val url = Constants.API_ENDPOINT + category + Constants.API_KEY
        val requestQueue = Volley.newRequestQueue(context)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            object : Response.Listener<JSONObject> {
                override fun onResponse(response: JSONObject?) {
                    listener.onSuccess(response)
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    listener.onFailed(error)
                }
            })
        requestQueue.add(jsonObjectRequest)
        requestQueue.cache.clear() //disable cache (so my SqLite Offline storage Logic would work)
    }

}