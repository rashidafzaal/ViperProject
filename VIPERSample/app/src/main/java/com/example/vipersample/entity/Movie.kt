package com.example.vipersample.entity

import android.os.Parcel
import android.os.Parcelable

class Movie() : Parcelable {
    var id: Int = 0
    var title: String? = ""
    var description: String? = ""
    var isAdult: Boolean? = false
    var rating: Double? = 0.0
    var type: String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        description = parcel.readString()
        isAdult = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        rating = parcel.readValue(Double::class.java.classLoader) as? Double
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeValue(isAdult)
        parcel.writeValue(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}