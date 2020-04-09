package com.novopay.bloggingapp.model

import android.os.Parcel
import android.os.Parcelable

class Posts(
    val userId: Int,
    val id: Int,
    val title: String?,
    val body: String?
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(userId)
        dest?.writeInt(id)
        dest?.writeString(title)
        dest?.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Posts> {
        override fun createFromParcel(parcel: Parcel): Posts {
            return Posts(parcel)
        }

        override fun newArray(size: Int): Array<Posts?> {
            return arrayOfNulls(size)
        }
    }

}