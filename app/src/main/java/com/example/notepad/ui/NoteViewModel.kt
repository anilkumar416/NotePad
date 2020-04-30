package com.example.notepad.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.notepad.persistence.Note
import com.example.notepad.repository.NoteRepository
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository
) : ViewModel() {

    fun insert(note: Note) {
        return noteRepository.insert(note)
    }

    fun delete(note: Note) {
        return noteRepository.delete(note)
    }

    fun deleteById(id: Int) {
        noteRepository.deleteBtId(id)
    }

    fun update(note: Note) {
        noteRepository.update(note)
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return noteRepository.getAllNotes()
    }

}