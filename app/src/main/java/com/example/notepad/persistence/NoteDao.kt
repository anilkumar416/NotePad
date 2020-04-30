package com.example.notepad.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("delete from tbl_note where id = :id")
    fun deleteById(id: Int)

    @Query("Select * from tbl_note")
    fun getAllNotes(): LiveData<List<Note>>

}