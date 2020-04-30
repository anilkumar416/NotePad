package com.example.notepad.repository

import androidx.lifecycle.LiveData
import com.example.notepad.persistence.Note
import com.example.notepad.persistence.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteRepository @Inject constructor(val noteDao: NoteDao) {

    //function to insert note in database
    fun insert(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.insert(note)
        }
    }

    //function to delete note in database
    fun delete(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.delete(note)
        }
    }

    //function to delete note by Id in database
    fun deleteBtId(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {

        }
    }

    //function to update note in database
    fun update(note: Note) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao.update(note)
        }
    }

    //function to get all nodes in database
    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getAllNotes()
    }


}