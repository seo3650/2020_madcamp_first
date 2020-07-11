package com.example.madcamp_first.Gallery

import android.app.Application
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GalleryViewModel(application: Application): AndroidViewModel(application) {
    val imageView: MutableLiveData<Bitmap> by lazy {
        MutableLiveData<Bitmap>()
    }

    fun updateImage(bitmap: Bitmap) {
        imageView.value = bitmap
    }

//    fun setImage(img: ImageView) {
//        imageView.value = img
//    }
}