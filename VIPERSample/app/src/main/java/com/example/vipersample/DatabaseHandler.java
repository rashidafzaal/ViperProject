package com.example.vipersample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vipersample.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "movies";

    // Movies table name
    private static final String TABLE_MOVIES = "movies_table";

    // Movies Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_RATING = "rating";
    private static final String KEY_ADULT = "adult";
    private static final String KEY_TYPE = "type";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_RATING + " TEXT,"
                + KEY_ADULT + " TEXT,"
                + KEY_TYPE + " TEXT"
                +")";
        db.execSQL(CREATE_MOVIES_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);

        // Create tables again
        onCreate(db);
    }

    // Adding new movie
    public void addMovie(Movie movie, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, movie.getTitle());
        values.put(KEY_DESCRIPTION, movie.getDescription());
        values.put(KEY_RATING, movie.getRating());
        values.put(KEY_ADULT, movie.isAdult());
        values.put(KEY_TYPE, type);

        // Inserting Row
        db.insert(TABLE_MOVIES, null, values);
        db.close(); // Closing database connection
    }

    public List<Movie> getAllMovies() {
        List<Movie> moviesList = new ArrayList<Movie>();
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Movie movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(0)));
                movie.setTitle(cursor.getString(1));
                movie.setDescription(cursor.getString(2));
                movie.setRating(Double.valueOf(cursor.getString(3)));
                movie.setAdult(Boolean.valueOf(cursor.getString(4)));
                movie.setType(cursor.getString(5));

                moviesList.add(movie);
            } while (cursor.moveToNext());
        }

        return moviesList;
    }
}