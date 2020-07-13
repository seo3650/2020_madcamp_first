package com.example.madcamp_first.Map

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "pin")
data class Pin (
    @PrimaryKey
    var position: String,
    @ColumnInfo(name = "name")
    var name: String
) {
    constructor(): this("", "")
}