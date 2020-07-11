package com.example.madcamp_first.Gallery

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.madcamp_first.Contact.ContactViewModel
import com.example.madcamp_first.R
import kotlinx.android.synthetic.main.activity_gallery.view.*
import java.io.File
import java.util.EnumSet.of


private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1
class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        findBy
//        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java).apply {
//            setImage()
//        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_CODE) {
                if (data != null) {
                    data.data?.let { sendPicture(it) }
                }
            }
            if (requestCode == CAMERA_CODE) {
                if (data != null) {
                    data.data?.let { sendPicture(it) }
                }
            }
        }
    }

    val CAMERA_CODE = 1111;
    val GALLERY_CODE =1112;
    fun getRealPathFromURI(uri: Uri): String? {
        var columnIndex = 0
        var proj = arrayOf(MediaStore.Images.Media.DATA)
        var cursor: Cursor = uri?.let { context?.contentResolver?.query(it, proj, null, null, null) }
            ?:return null
        if (cursor.moveToFirst()) { columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) }
        return cursor.getString(columnIndex)
    }

    fun sendPicture(imgUri:Uri) {
        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) }
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
        } // TODO: Consider denied permission

        var imagePath = getRealPathFromURI(imgUri) // path 경로
        val bitmap = BitmapFactory.decodeFile(imagePath)//경로를 통해 비트맵으로 전환
        galleryViewModel.updateImage(bitmap)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_gallery, container, false)
        var i = 1
        var drawable = R.drawable.barley

        /* Observe user's image */
        var imageView: ImageView = root.findViewById(R.id.imageView)
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        galleryViewModel.imageView.observe(this, Observer<Bitmap> {
            imageView.setImageBitmap(it)
        })
//        fun select_big_image(){
//            if (i == 1) {
//                root.Bigscreen.setImageDrawable(root.imageButton1.drawable)
//            }
//            if (i == 2) {
//                root.Bigscreen.setImageDrawable(root.imageButton2.drawable)
//            }
//            if (i == 3) {
//                root.Bigscreen.setImageDrawable(root.imageButton3.drawable)
//            }
//        }
//        fun go_to_bigscreen() {
//            root.scrollView1.visibility = View.INVISIBLE
//            root.Bigscreen.visibility = View.VISIBLE
//            root.goOut.visibility = View.VISIBLE
//            root.goNext.visibility = View.VISIBLE
//            root.goPrev.visibility = View.VISIBLE
//            select_big_image()
//
//            root.goNext.setOnClickListener {
//                if (i < 3){
//                    i +=1
//                    select_big_image()
//                }
//            }
//            root.goPrev.setOnClickListener {
//                if (i > 1){
//                    i -=1
//                    select_big_image()
//                }
//            }
//            root.goOut.setOnClickListener { //go back to thumbnail
//                root.scrollView1.visibility = View.VISIBLE
//                root.Bigscreen.visibility = View.INVISIBLE
//                root.goOut.visibility = View.INVISIBLE
//                root.goNext.visibility = View.INVISIBLE
//                root.goPrev.visibility = View.INVISIBLE
//            }
//        }


        var intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_CODE)


//            root.imageButton1.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//
//            root.imageButton2.setOnClickListener {
//                i = 2
//                go_to_bigscreen()
//            }
//            root.imageButton3.setOnClickListener {
//                i = 3
//                go_to_bigscreen()
//            }
//            root.imageButton4.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton5.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton6.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton7.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton8.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton9.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton10.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton11.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton12.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton13.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton14.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton15.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton16.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton17.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton18.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton19.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }
//            root.imageButton20.setOnClickListener {
//                i = 1
//                go_to_bigscreen()
//            }

            return root
        }


    companion object {
        @JvmStatic
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }
}