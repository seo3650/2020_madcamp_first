package com.example.madcamp_first.Map

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData

class PinRepository(application: Application) {

    private val pinDatabase = PinDatabase.getInstance(application)!!
    private val pinDao: PinDao = pinDatabase.pinDao()
    private val pins: LiveData<List<Pin>> = pinDao.getAll()

    fun getAll(): LiveData<List<Pin>> {
        return pins
    }

    fun insert(pin: Pin) {
        try {
            val thread = Thread(Runnable {
                pinDao.insert(pin) })
            thread.start()
        } catch (e: Exception) { }
        Log.d("pins", pins.value.toString())
    }

    fun delete(pin: Pin) {
        try {
            val thread = Thread(Runnable {
                pinDao.delete(pin)
            })
            thread.start()
        } catch (e: Exception) { }
    }

}