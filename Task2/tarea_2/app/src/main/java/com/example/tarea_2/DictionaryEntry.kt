package com.example.tarea_2


import android.os.Parcel
import android.os.Parcelable

class DictionaryEntry(val key: String?, val value: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DictionaryEntry> {
        override fun createFromParcel(parcel: Parcel): DictionaryEntry {
            return DictionaryEntry(parcel)
        }

        override fun newArray(size: Int): Array<DictionaryEntry?> {
            return arrayOfNulls(size)
        }
    }
}