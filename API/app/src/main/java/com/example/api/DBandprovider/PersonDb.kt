package com.example.api.DBandprovider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "rick_and_morty1")
data class PersonDb(
    @PrimaryKey var uuid:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") var name: String = "woman",
    @ColumnInfo(name = "gender") var gender: String = "shlyapa",
    @ColumnInfo(name = "image") var image:String ?,
    @ColumnInfo(name = "status") var status:String?,
    @ColumnInfo(name = "species") var species:String?,
    @ColumnInfo(name = "id") var id:Int?,
    @ColumnInfo(name = "favorite") var favorite:Boolean
): Serializable