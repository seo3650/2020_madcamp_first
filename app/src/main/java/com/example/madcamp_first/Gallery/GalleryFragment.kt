package com.example.madcamp_first.Gallery

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    private val CAMERA_CODE = 1111
    private val GALLERY_CODE =1112

    private fun getRealPathFromURI(uri: Uri): String? {
        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = uri.let { context?.contentResolver?.query(it, proj, null, null, null) }
            ?:return null
        if (cursor.moveToFirst()) { columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) }
        return cursor.getString(columnIndex)
    }

    private fun sendPicture(imgUri:Uri) {
        if (context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.READ_EXTERNAL_STORAGE) }
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
            )
        }
        val imagePath = getRealPathFromURI(imgUri) // path 경로
        val bitmap = getResizePicture(imagePath)
        galleryViewModel.updateImage(bitmap)
        new_photo_pointer += 1
    }

    private var screened_big_image = 1
    private var new_photo_pointer = 1

    private fun init_new_photo_pointer(view: View){
        for (i in 1..20){
            val imgViewID = "imageButton$i"
            val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
            val imgview : ImageButton = view.findViewById(resID)
            if (imgview.resources == null) {
                new_photo_pointer = i
                break
            } else { new_photo_pointer +=1 }
        }
    }

    private fun select_view_drawable(view:View):Drawable?{
        val imgViewID = "imageButton$screened_big_image"
        val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
        val imgview : ImageButton = view.findViewById(resID)
        return imgview.drawable
    }

    private fun go_big(view:View){
        view.scrollView1.visibility = View.INVISIBLE
        view.add_button.visibility = View.INVISIBLE
        view.Bigscreen.visibility = View.VISIBLE
        view.goOut.visibility = View.VISIBLE
        view.delete_button.visibility = View.VISIBLE
        view.goNext.visibility = View.VISIBLE
        view.goPrev.visibility = View.VISIBLE
    }
    private fun go_small(view: View){
        view.scrollView1.visibility = View.VISIBLE
        view.add_button.visibility = View.VISIBLE
        view.Bigscreen.visibility = View.INVISIBLE
        view.goOut.visibility = View.INVISIBLE
        view.delete_button.visibility = View.INVISIBLE
        view.goNext.visibility = View.INVISIBLE
        view.goPrev.visibility = View.INVISIBLE
    }

    private fun go_to_bigscreen(view:View) {
        go_big(view)
        view.Bigscreen.setImageDrawable(select_view_drawable(view))

        view.goNext.setOnClickListener {
            if (screened_big_image < new_photo_pointer - 1){ // 마지막 사진에서는 Next가 작동하지 않음
                screened_big_image += 1
                view.Bigscreen.setImageDrawable(select_view_drawable(view))
            }
        }
        view.goPrev.setOnClickListener {
            if (screened_big_image > 1){ // 처음 사진에서는 Prev가 작동하지 않음
                screened_big_image -=1
                view.Bigscreen.setImageDrawable(select_view_drawable(view))
            }
        }
        view.delete_button.setOnClickListener {
            val builder = AlertDialog.Builder(context as Activity)
            val dialogView = layoutInflater.inflate(R.layout.alert_gallery, null)
            builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->
                    /* 확인일 때 */
                    delete_image(view)
                    new_photo_pointer -= 1
                    if(screened_big_image == new_photo_pointer){
                        screened_big_image -=1
                    }
                    if(screened_big_image == 0){
                        go_small(view)
                    } else {view.Bigscreen.setImageDrawable(select_view_drawable(view))}
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    /* 취소일 때 아무 액션이 없으므로 빈칸 */
                }
                .show()
        }

        view.goOut.setOnClickListener { //go back to thumbnail
            go_small(view)
        }

        return
    }

    private fun getResizePicture(imagePath: String?): Bitmap { //사진 용량을 줄여주는 함수
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(imagePath, options)
        val resize = 1000
        var width = options.outWidth
        var height = options.outHeight
        var sampleSize = 1
        while (true) {
            if (width / 2 < resize || height / 2 < resize)
                break
            width /= 2
            height /= 2
            sampleSize *= 2
        }
        options.inSampleSize = sampleSize
        options.inJustDecodeBounds = false

        val resizeBitmap = BitmapFactory.decodeFile(imagePath, options)
        val exit = imagePath?.let { ExifInterface(it) }
        var exifDegree = 0F
        exit?.let {
            val exifOrientation = it.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            exifDegree = exifOreintationToDegrees(exifOrientation)
        }
        return roteateBitmap(resizeBitmap, exifDegree)

    }

    private fun exifOreintationToDegrees(exifOrientation:Int):Float { // 사진이 얼마나 돌아가 있는지 알려주는 함수
        return when (exifOrientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                90F
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                180F
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                270F
            }
            else -> 0F
        }
    }

    private fun roteateBitmap(src:Bitmap, degree:Float): Bitmap { // 똑바르게 서도록 회전시키는 함수
        // Matrix 객체 생성
        val matrix = Matrix()
        // 회전 각도 셋팅
        matrix.postRotate(degree)
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
        }

    private fun delete_image(view: View) {

        val imgViewID = "imageButton$screened_big_image"
        val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
        val imgview : ImageButton = view.findViewById(resID)
        imgview.setImageResource(0)

        for (i in screened_big_image..19){
            val imgViewID1 = "imageButton$i"
            val resID1 = resources.getIdentifier(imgViewID1, "id", context?.packageName)
            val imgview1 : ImageButton = view.findViewById(resID1)

            val j = i+1
            val imgViewID2 = "imageButton$j"
            val resID2 = resources.getIdentifier(imgViewID2, "id", context?.packageName)
            val imgview2 : ImageButton = view.findViewById(resID2)
            imgview1.setImageDrawable(imgview2.drawable)
        }
        view.imageButton20.setImageResource(0)
    }


    private fun addImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_CODE)
    }

    private var imageView: ImageButton? = null //새로운 사진을 담을 View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_gallery, container, false)

        /* Observe user's image */
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        galleryViewModel.imageView.observe(this, Observer<Bitmap> {
            imageView?.setImageBitmap(it)
        })

        /*initiate new photo pointer*/
        init_new_photo_pointer(root)

        for (i in 1..20){
            val imgViewID = "imageButton$i"
            val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
            val imgview : ImageButton = root.findViewById(resID)
            imgview.setOnClickListener{
                if (new_photo_pointer > i) {
                    screened_big_image = i
                    go_to_bigscreen(root) }
            }
        }

        /* Add new image */
        val addButton = root.findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            if (new_photo_pointer>20) {
                Toast.makeText(context as Activity, "공간이 부족합니다", Toast.LENGTH_SHORT).show()
            } else {
            val imgViewID = "imageButton$new_photo_pointer"
            val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
            val imgview : ImageButton = root.findViewById(resID)
            imageView = imgview
            addImage()
            }
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