package com.amineaytac.mynotesapp.Repository

import androidx.lifecycle.LiveData
import com.amineaytac.mynotesapp.Database.dao.NotesDao
import com.amineaytac.mynotesapp.Model.Notes

class NotesRepository (val dao : NotesDao) {

    fun getAllNotes() :LiveData<List<Notes>> = dao.getNotes()

    fun getHighNotes() :LiveData<List<Notes>> = dao.getHighNotes()

    fun getMediumNotes() :LiveData<List<Notes>> = dao.getMediumNotes()

    fun getLowNotes() :LiveData<List<Notes>> = dao.getLowNotes()

     fun insertNotes(notes: Notes) {
        return dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int) {
        return dao.deleteNotes(id)
    }

     fun updateNotes(notes:Notes)  {
        return dao.updateNotes(notes)
    }
}