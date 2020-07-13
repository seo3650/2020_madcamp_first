package com.example.madcamp_first.Gallery

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class GalleryViewModel(application: Application): AndroidViewModel(application) {
    val imageView: MutableLiveData<Bitmap> by lazy {
        MutableLiveData<Bitmap>()
    }

    fun updateImage(bitmap: Bitmap) {
        imageView.value = bitmap
    }

}