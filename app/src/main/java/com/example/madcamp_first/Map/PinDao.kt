package com.example.madcamp_first.Map

import androidx.lifecycle.LiveData
import androidx.room.*
import com.google.android.gms.maps.model.LatLng

@Dao
interface PinDao {
    @Query("SELECT * FROM pin")
    fun getAll(): LiveData<List<Pin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pin: Pin)

    @Delete
    fun delete(pin: Pin)
}