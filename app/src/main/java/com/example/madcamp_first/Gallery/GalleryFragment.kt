package com.example.madcamp_first.Gallery

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.madcamp_first.Contact.ContactViewModel
import com.example.madcamp_first.R
import kotlinx.android.synthetic.main.activity_gallery.view.*

class GalleryFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_CODE) {
                if (data != null) {
                    sendPicture(data.data, ) }
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

    fun sendPicture(imgUri:Uri, imageView: ImageView) {
        var imagePath = getRealPathFromURI(imgUri); // path 경로
        val bitmap = BitmapFactory.decodeFile(imagePath);//경로를 통해 비트맵으로 전환
        imageView.setImageBitmap(bitmap);//이미지 뷰에 비트맵 넣기
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_gallery, container, false)
        var i = 1
        var drawable = R.drawable.barley

        fun select_big_image(){
            if (i == 1) {
                root.Bigscreen.setImageDrawable(root.imageButton1.drawable)
            }
            if (i == 2) {
                root.Bigscreen.setImageDrawable(root.imageButton2.drawable)
            }
            if (i == 3) {
                root.Bigscreen.setImageDrawable(root.imageButton3.drawable)
            }
        }
        fun go_to_bigscreen() {
            root.scrollView1.visibility = View.INVISIBLE
            root.Bigscreen.visibility = View.VISIBLE
            root.goOut.visibility = View.VISIBLE
            root.goNext.visibility = View.VISIBLE
            root.goPrev.visibility = View.VISIBLE
            select_big_image()

            root.goNext.setOnClickListener {
                if (i < 3){
                    i +=1
                    select_big_image()
                }
            }
            root.goPrev.setOnClickListener {
                if (i > 1){
                    i -=1
                    select_big_image()
                }
            }
            root.goOut.setOnClickListener { //go back to thumbnail
                root.scrollView1.visibility = View.VISIBLE
                root.Bigscreen.visibility = View.INVISIBLE
                root.goOut.visibility = View.INVISIBLE
                root.goNext.visibility = View.INVISIBLE
                root.goPrev.visibility = View.INVISIBLE
            }
        }




        var intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_CODE)
        onActivityResult(GALLERY_CODE, RESULT_OK, intent)


            root.imageButton1.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }

            root.imageButton2.setOnClickListener {
                i = 2
                go_to_bigscreen()
            }
            root.imageButton3.setOnClickListener {
                i = 3
                go_to_bigscreen()
            }
            root.imageButton4.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton5.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton6.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton7.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton8.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton9.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton10.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton11.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton12.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton13.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton14.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton15.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton16.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton17.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton18.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton19.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }
            root.imageButton20.setOnClickListener {
                i = 1
                go_to_bigscreen()
            }

            return root
        }


    companion object {
        @JvmStatic
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }
}