package com.example.notepad.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

//Database class
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}