package com.example.api.DBandprovider

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "persons")
data class PersonDb(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false

)