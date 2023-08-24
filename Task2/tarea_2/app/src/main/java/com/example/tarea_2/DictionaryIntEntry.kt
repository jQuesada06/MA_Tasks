package com.example.tarea_2

import android.os.Parcel
import android.os.Parcelable


class DictionaryIntEntry(val key: String?, val value: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeInt(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DictionaryIntEntry> {
        override fun createFromParcel(parcel: Parcel): DictionaryIntEntry {
            return DictionaryIntEntry(parcel)
        }

        override fun newArray(size: Int): Array<DictionaryIntEntry?> {
            return arrayOfNulls(size)
        }
    }
}