package com.example.vipersample.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vipersample.*
import com.example.vipersample.adapter.MoviesAdapter
import com.example.vipersample.entity.Movie
import com.example.vipersample.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainImpl.View, CategoryDialogFragment.CategorySelectListener{

    private lateinit var db: DatabaseHandler //Offline Storage (SqLite)
    private var adapter: MoviesAdapter? = null
    private var presenter: MainImpl.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHandler(this)
        presenter = MainPresenter(this, this, db)

        //setup Adapter
        rvMovies.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = MoviesAdapter(this,
            null,
            { movie -> presenter?.onListItemClicked(movie) })
        rvMovies.adapter = adapter

        //Load data
        presenter?.loadMoviesData(Constants.CATEGORY_POPULAR)
        setClickListener()
    }

    private fun setClickListener() {
        etSelectMovie.setOnClickListener {
            //open categories popup
            val fm = supportFragmentManager
            val dialogFragment = CategoryDialogFragment.newInstance()
            dialogFragment?.setCategorySelectListener(this)
            dialogFragment?.show(fm, "testFrag")
        }
        etSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //search
                adapter?.getFilter()?.filter(text)
            }
        })
    }
    override fun onCategorySelected(category: String?) {
        etSelectMovie.setText(category)
        presenter?.loadMoviesData(Utils.setApiTextAgainstSimpleText(category!!))
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }
    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    override fun publishList(list: List<Movie>) {
        (rvMovies.adapter as MoviesAdapter).updateFilteredList(list)
        (rvMovies.adapter as MoviesAdapter).updateData(list)

        //add data in SQLite only first time
        // Offline storage handled for Popular movies
        if (db.allMovies.size > 0) {
        } else {
            // add data
            for (i in 0 until list.size) {
                db.addMovie(list.get(i), Constants.POPULAR_TEXT)
            }
        }
    }

}