package com.example.madcamp_first.Gallery

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.madcamp_first.R
import kotlinx.android.synthetic.main.activity_gallery.view.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var currentPhotoPath = ""

    private fun addImagefromCamera(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(activity!!.packageManager)?.also {
                val photoFile: File? = try{
                    createImageFile()
                }catch(ex:IOException){
                    null
                }
                photoFile?.also{
                    val photoURI : Uri = FileProvider.getUriForFile(
                        context as Activity,
                        "com.example.madcamp_first.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, cameraCode)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile() : File{
        val timeStamp : String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir : File? = context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply{
            currentPhotoPath = absolutePath
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == galleryCode) {
                if (data != null) {
                    data.data?.let { sendPicture(it) }
                }
            }
            if (requestCode == cameraCode) {
                val newbitmap = getResizePicture(currentPhotoPath)
                imageView?.setImageBitmap(newbitmap)
                newPhotoPointer += 1
                    }
                }
            }

    private val cameraCode = 1111
    private val galleryCode = 1112

    private fun getRealPathFromURI(uri: Uri): String? {
        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = uri.let { context?.contentResolver?.query(it, proj, null, null, null) }
            ?:return null
        if (cursor.moveToFirst()) { columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) }
        return cursor.getString(columnIndex)
    }

    private fun sendPicture(imgUri:Uri) {
        val imagePath = getRealPathFromURI(imgUri) // path 경로
        val bitmap = getResizePicture(imagePath)
        galleryViewModel.updateImage(bitmap)
        newPhotoPointer += 1
    }

    private var screenedBigImage = 1
    private var newPhotoPointer = 1


    private fun initNewPhotoPointer(view: View){
        for (i in 1..20){
            val imgViewID = "imageButton$i"
            val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
            val imgview : ImageButton = view.findViewById(resID)
            if (imgview.drawable == null) {
                newPhotoPointer = i
                break
            } else { newPhotoPointer +=1 }
        }
    }

    private fun selectViewDrawable(view:View):Drawable?{
        val imgViewID = "imageButton$screenedBigImage"
        val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
        val imgview : ImageButton = view.findViewById(resID)
        return imgview.drawable
    }

    private fun goBig(view:View){
        view.scrollView1.visibility = View.INVISIBLE
        view.add_button.visibility = View.INVISIBLE
        view.camera_button.visibility = View.INVISIBLE
        view.Bigscreen.visibility = View.VISIBLE
        view.goOut.visibility = View.VISIBLE
        view.delete_button.visibility = View.VISIBLE
        view.goNext.visibility = View.VISIBLE
        view.goPrev.visibility = View.VISIBLE
    }

    private fun goSmall(view: View){
        view.scrollView1.visibility = View.VISIBLE
        view.add_button.visibility = View.VISIBLE
        view.camera_button.visibility = View.VISIBLE
        view.Bigscreen.visibility = View.INVISIBLE
        view.goOut.visibility = View.INVISIBLE
        view.delete_button.visibility = View.INVISIBLE
        view.goNext.visibility = View.INVISIBLE
        view.goPrev.visibility = View.INVISIBLE
    }

    private fun goToBigscreen(view:View) {
        goBig(view)
        view.Bigscreen.setImageDrawable(selectViewDrawable(view))

        view.goNext.setOnClickListener {
            if (screenedBigImage < newPhotoPointer - 1){ // 마지막 사진에서는 Next가 작동하지 않음
                screenedBigImage += 1
                view.Bigscreen.setImageDrawable(selectViewDrawable(view))
            }
        }
        view.goPrev.setOnClickListener {
            if (screenedBigImage > 1){ // 처음 사진에서는 Prev가 작동하지 않음
                screenedBigImage -=1
                view.Bigscreen.setImageDrawable(selectViewDrawable(view))
            }
        }
        view.delete_button.setOnClickListener {
            val builder = AlertDialog.Builder(context as Activity)
            val dialogView = layoutInflater.inflate(R.layout.alert_gallery, null)
            builder.setView(dialogView)
                .setPositiveButton("확인") { _, _ ->
                    /* 확인일 때 */
                    deleteImage(view)
                    newPhotoPointer -= 1
                    if(screenedBigImage == newPhotoPointer){
                        screenedBigImage -=1
                    }
                    if(screenedBigImage == 0){
                        goSmall(view)
                    } else {view.Bigscreen.setImageDrawable(selectViewDrawable(view))}
                }
                .setNegativeButton("취소") { _, _ ->
                    /* 취소일 때 아무 액션이 없으므로 빈칸 */
                }
                .show()
        }

        view.goOut.setOnClickListener { //go back to thumbnail
            goSmall(view)
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
        return rotateBitmap(resizeBitmap, exifDegree)

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

    private fun rotateBitmap(src:Bitmap, degree:Float): Bitmap { // 똑바르게 서도록 회전시키는 함수
        // Matrix 객체 생성
        val matrix = Matrix()
        // 회전 각도 셋팅
        matrix.postRotate(degree)
        // 이미지와 Matrix 를 셋팅해서 Bitmap 객체 생성
        return Bitmap.createBitmap(src, 0, 0, src.width, src.height, matrix, true)
        }

    private fun deleteImage(view: View) {

        val imgViewID = "imageButton$screenedBigImage"
        val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
        val imgview : ImageButton = view.findViewById(resID)
        imgview.setImageResource(0)

        for (i in screenedBigImage..19){
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


    private fun addImagefromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        intent.type = "image/*"
        startActivityForResult(intent, galleryCode)
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
        initNewPhotoPointer(root)

        for (i in 1..20){
            val imgViewID = "imageButton$i"
            val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
            val imgview : ImageButton = root.findViewById(resID)
            imgview.setOnClickListener{
                if (newPhotoPointer > i) {
                    screenedBigImage = i
                    goToBigscreen(root) }
            }
        }

        /* Add new image */
        val addButton = root.findViewById<Button>(R.id.add_button)
        addButton.setOnClickListener {
            if (newPhotoPointer>20) {
                Toast.makeText(context as Activity, "공간이 부족합니다", Toast.LENGTH_SHORT).show()
            } else {
            val imgViewID = "imageButton$newPhotoPointer"
            val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
            val imgview : ImageButton = root.findViewById(resID)
            imageView = imgview
            addImagefromGallery()
            }
        }
        val cameraButton = root.findViewById<Button>(R.id.camera_button)
        cameraButton.setOnClickListener {
            if (newPhotoPointer>20) {
                Toast.makeText(context as Activity, "공간이 부족합니다", Toast.LENGTH_SHORT).show()
            } else {
                val imgViewID = "imageButton$newPhotoPointer"
                val resID = resources.getIdentifier(imgViewID, "id", context?.packageName)
                val imgview : ImageButton = root.findViewById(resID)
                imageView = imgview
                addImagefromCamera()
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