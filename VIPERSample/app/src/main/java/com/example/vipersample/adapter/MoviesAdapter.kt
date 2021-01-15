package com.example.vipersample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.vipersample.R
import com.example.vipersample.entity.Movie
import kotlinx.android.synthetic.main.custom_item.view.*
import java.util.*
import kotlin.collections.ArrayList


class MoviesAdapter (private var context: Context,
                     private var moviesList: List<Movie>?,
                     private var listener: (Movie?) -> Unit)
    :RecyclerView.Adapter<MoviesAdapter.ViewHolder>(), Filterable{

    var mainFilteredList: List<Movie>? = moviesList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewRow = LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(viewRow)
    }

    override fun getItemCount(): Int {
        return moviesList?.size?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val obj = moviesList?.get(position)

        holder.tvTitle?.text = obj?.title
        holder.tvDescription?.text = obj?.description
        holder.tvRating?.text = obj?.rating.toString()
        holder.tvAdult?.text = if (obj?.isAdult == true)
            context.resources.getString(R.string.eighteenPlus)
        else
            context.resources.getString(R.string.lessThanEighteen)

        holder.clLayout?.setOnClickListener { listener(obj) }
    }

    // For Fast Performance
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView? = itemView.tvTitle
        val tvDescription: TextView? = itemView.tvDescription
        val tvRating: TextView? = itemView.tvRating
        val tvAdult: TextView? = itemView.tvAdult
        val clLayout: ConstraintLayout? = itemView.clLayout
    }

    fun updateFilteredList(list: List<Movie>) {
        this.mainFilteredList = list
    }

    fun updateData(list: List<Movie>) {
        this.moviesList = list
        this.notifyDataSetChanged()
    }

    //search movie by title
    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    moviesList = mainFilteredList
                } else {
                    val filteredList: MutableList<Movie> = ArrayList()
                    if (mainFilteredList !=null){
                        for (row in mainFilteredList!!) {
                            if (row.title.toString().toLowerCase(Locale.getDefault()).
                                contains(charString.toLowerCase(Locale.getDefault()))) {
                                filteredList.add(row)
                            }
                        }
                    }
                    if (filteredList.size > 0) {
                        moviesList = filteredList
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = moviesList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                moviesList = filterResults.values as ArrayList<Movie>?
                notifyDataSetChanged()
            }
        }
    }
}