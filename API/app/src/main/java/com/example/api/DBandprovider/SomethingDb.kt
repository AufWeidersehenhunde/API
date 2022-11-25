package com.example.architecturecomponent.dpandprovider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "txt")
data class SomethingDb(
    @PrimaryKey var uuid:String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "url") var url: String?
): Serializable