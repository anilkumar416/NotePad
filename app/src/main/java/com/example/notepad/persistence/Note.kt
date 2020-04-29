package com.example.notepad.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

// - Entity class for note table
// - A Parcelable is the Android implementation of the Java Serializable
// - It is used to transfer data between activities or fragments

@Entity(tableName = "tbl_note")
class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String?,
    var description: String?,
    var tag: String?
)