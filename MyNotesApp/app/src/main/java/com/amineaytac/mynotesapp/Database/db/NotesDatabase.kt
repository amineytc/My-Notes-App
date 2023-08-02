package com.amineaytac.mynotesapp.Database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amineaytac.mynotesapp.Database.dao.NotesDao
import com.amineaytac.mynotesapp.Model.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesDatabase :RoomDatabase(){
    abstract fun myNotesDao(): NotesDao

    companion object
    {
        @Volatile
        var INSTANCE: NotesDatabase?=null

        @Synchronized
        fun getDatabaseInstance(context: Context): NotesDatabase {
            if(INSTANCE ==null){
               INSTANCE =  Room.databaseBuilder(
                   context,
                   NotesDatabase::class.java,
                   "Notes").allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }

    }
}