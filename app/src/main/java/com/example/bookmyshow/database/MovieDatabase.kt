package com.example.bookmyshow.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devfest.india.bmsclone.data.local.database.typeconverter.MovieTypeConverter
import com.example.bookmyshow.constant.Constant
import com.example.bookmyshow.dao.MovieDao
import com.example.bookmyshow.model.MovieResponse

@Database(entities = [MovieResponse::class], version = 1)
@TypeConverters(MovieTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        private var INSTANCE: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDatabase::class.java,
                        Constant.DATABASE_NAME
                    ).build()
                }
                return INSTANCE as MovieDatabase
            }
        }
    }

    abstract fun movieDao(): MovieDao
}