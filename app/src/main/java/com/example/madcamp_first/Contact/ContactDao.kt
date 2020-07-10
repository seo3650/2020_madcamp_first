package com.example.madcamp_first.Contact

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.madcamp_first.Contact.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)
}