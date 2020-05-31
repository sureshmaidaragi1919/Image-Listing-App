package com.maida.imagelistapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "image_path_table")
data class ImageEntity(@PrimaryKey @ColumnInfo(name = "path") val path: String)
