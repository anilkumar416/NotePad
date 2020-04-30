package com.example.notepad.persistence

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Note> {
        override fun createFromParcel(parcel: Parcel): Note {
            return Note(parcel)
        }

        override fun newArray(size: Int): Array<Note?> {
            return arrayOfNulls(size)
        }
    }
}