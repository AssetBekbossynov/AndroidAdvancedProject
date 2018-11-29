package com.example.asset.todoproject.models

import android.os.Parcel
import android.os.Parcelable

data class Todo (var userId: String,
                 var description: String,
                 var createdDate: String,
                 var planDate: String,
                 var priority: String,
                 var tagId: String): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    constructor(): this("", "", "", "", "", "")
    override fun toString(): String {
        return "Todo(userId='$userId', description='$description', createdDate='$createdDate', planDate='$planDate', priority='$priority', tagId='$tagId')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeString(description)
        parcel.writeString(createdDate)
        parcel.writeString(planDate)
        parcel.writeString(priority)
        parcel.writeString(tagId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Todo> {
        override fun createFromParcel(parcel: Parcel): Todo {
            return Todo(parcel)
        }

        override fun newArray(size: Int): Array<Todo?> {
            return arrayOfNulls(size)
        }
    }
}