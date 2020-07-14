package com.example.madcamp_first.Map

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pin::class], version = 3)
abstract class PinDatabase: RoomDatabase() {
    abstract fun pinDao(): PinDao

    companion object {
        private var INSTANCE: PinDatabase? = null

        fun getInstance(context: Context): PinDatabase? {
            if (INSTANCE == null) {
                synchronized(PinDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PinDatabase::class.java, "pin")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}