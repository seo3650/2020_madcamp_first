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
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.madcamp_first.R
import kotlinx.android.synthetic.main.activity_gallery.view.*

private const val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        new_photo_pointer += 1
    }

    var screened_big_image = 1
    var new_photo_pointer = 10

    private fun select_big_image(view: View){
        if (screened_big_image == 1) {
            view.Bigscreen.setImageDrawable(view.imageButton1.drawable)
        }
        if (screened_big_image == 2) {
            view.Bigscreen.setImageDrawable(view.imageButton2.drawable)
        }
        if (screened_big_image == 3) {
            view.Bigscreen.setImageDrawable(view.imageButton3.drawable)
        }
        if (screened_big_image == 4) {
            view.Bigscreen.setImageDrawable(view.imageButton4.drawable)
        }
        if (screened_big_image == 5) {
            view.Bigscreen.setImageDrawable(view.imageButton5.drawable)
        }
        if (screened_big_image == 6) {
            view.Bigscreen.setImageDrawable(view.imageButton6.drawable)
        }
        if (screened_big_image == 7) {
            view.Bigscreen.setImageDrawable(view.imageButton7.drawable)
        }
        if (screened_big_image == 8) {
            view.Bigscreen.setImageDrawable(view.imageButton8.drawable)
        }
        if (screened_big_image == 9) {
            view.Bigscreen.setImageDrawable(view.imageButton9.drawable)
        }
        if (screened_big_image == 10) {
            view.Bigscreen.setImageDrawable(view.imageButton10.drawable)
        }
        if (screened_big_image == 11) {
            view.Bigscreen.setImageDrawable(view.imageButton11.drawable)
        }
        if (screened_big_image == 12) {
            view.Bigscreen.setImageDrawable(view.imageButton12.drawable)
        }
        if (screened_big_image == 13) {
            view.Bigscreen.setImageDrawable(view.imageButton13.drawable)
        }
        if (screened_big_image == 14) {
            view.Bigscreen.setImageDrawable(view.imageButton14.drawable)
        }
        if (screened_big_image == 15) {
            view.Bigscreen.setImageDrawable(view.imageButton15.drawable)
        }
        if (screened_big_image == 16) {
            view.Bigscreen.setImageDrawable(view.imageButton16.drawable)
        }
        if (screened_big_image == 17) {
            view.Bigscreen.setImageDrawable(view.imageButton17.drawable)
        }
        if (screened_big_image == 18) {
            view.Bigscreen.setImageDrawable(view.imageButton18.drawable)
        }
        if (screened_big_image == 19) {
            view.Bigscreen.setImageDrawable(view.imageButton19.drawable)
        }
        if (screened_big_image == 20) {
            view.Bigscreen.setImageDrawable(view.imageButton20.drawable)
        }
    }

    private fun go_to_bigscreen(view:View) {
        view.scrollView1.visibility = View.INVISIBLE
        view.Bigscreen.visibility = View.VISIBLE
        view.goOut.visibility = View.VISIBLE
        view.goNext.visibility = View.VISIBLE
        view.goPrev.visibility = View.VISIBLE

        select_big_image(view)
        view.goNext.setOnClickListener {
            if (screened_big_image < new_photo_pointer){ // 마지막 사진에서는 Next가 작동하지 않음
                screened_big_image += 1
                select_big_image(view)
            }
        }
        view.goPrev.setOnClickListener {
            if (screened_big_image > 1){ // 처음 사진에서는 Prev가 작동하지 않음
                screened_big_image -=1
                select_big_image(view)
            }
        }
        view.goOut.setOnClickListener { //go back to thumbnail
            view.scrollView1.visibility = View.VISIBLE
            view.Bigscreen.visibility = View.INVISIBLE
            view.goOut.visibility = View.INVISIBLE
            view.goNext.visibility = View.INVISIBLE
            view.goPrev.visibility = View.INVISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_gallery, container, false)

        /* Observe user's image */
        var imageView: ImageButton = root.findViewById(R.id.imageButton11)
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        galleryViewModel.imageView.observe(this, Observer<Bitmap> {
            imageView.setImageBitmap(it)
        })

        if (new_photo_pointer >= 1) {
            root.imageButton1.setOnClickListener {
                screened_big_image = 1
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 2) {
            root.imageButton2.setOnClickListener {
                screened_big_image = 2
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 3) {
            root.imageButton3.setOnClickListener {
                screened_big_image = 3
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 4) {
            root.imageButton4.setOnClickListener {
                screened_big_image = 4
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 5) {
            root.imageButton5.setOnClickListener {
                screened_big_image = 5
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 6) {
            root.imageButton6.setOnClickListener {
                screened_big_image = 6
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 7) {
            root.imageButton7.setOnClickListener {
                screened_big_image = 7
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 8 ) {
            root.imageButton8.setOnClickListener {
                screened_big_image = 8
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 9) {
            root.imageButton9.setOnClickListener {
                screened_big_image = 9
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 10) {
            root.imageButton10.setOnClickListener {
                screened_big_image = 10
                go_to_bigscreen(root) }
        }

            root.imageButton11.setOnClickListener {
                if (new_photo_pointer >= 11) {
                screened_big_image = 11
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 12) {
            root.imageButton12.setOnClickListener {
                screened_big_image = 12
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 13) {
            root.imageButton13.setOnClickListener {
                screened_big_image = 13
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 14) {
            root.imageButton14.setOnClickListener {
                screened_big_image = 14
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 15) {
            root.imageButton15.setOnClickListener {
                screened_big_image = 15
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 16) {
            root.imageButton16.setOnClickListener {
                screened_big_image = 16
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 17) {
            root.imageButton17.setOnClickListener {
                screened_big_image = 17
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 18) {
            root.imageButton18.setOnClickListener {
                screened_big_image = 18
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 19) {
            root.imageButton19.setOnClickListener {
                screened_big_image = 19
                go_to_bigscreen(root) }
        }
        if (new_photo_pointer >= 20) {
            root.imageButton20.setOnClickListener {
                screened_big_image = 20
                go_to_bigscreen(root) }
        }

        /* Add new image */
        val addButton = root.findViewById<Button>(R.id.gallery_add_button)

        addButton.setOnClickListener {
            addImage()
        }

        return root
    }

    fun addImage() {
        var intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_CODE)
    }


    companion object {
        @JvmStatic
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }
}