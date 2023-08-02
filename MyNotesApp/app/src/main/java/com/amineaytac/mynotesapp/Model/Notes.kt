package com.amineaytac.mynotesapp.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
class Notes (
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    var title:String,
    var notes:String,
    var date:String,
    var subTitle : String,
    var priority:String) : Parcelable {

}