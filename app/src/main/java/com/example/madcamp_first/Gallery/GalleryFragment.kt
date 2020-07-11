package com.example.madcamp_first.Gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.madcamp_first.Contact.ContactViewModel
import com.example.madcamp_first.R
import kotlinx.android.synthetic.main.activity_gallery.view.*

class GalleryFragment : Fragment() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    private fun go_to_bigscreen(root:View) {
        root.scrollView1.visibility = View.INVISIBLE
        root.Bigscreen.visibility = View.VISIBLE
        root.goOut.visibility = View.VISIBLE
        root.goNext.visibility = View.VISIBLE
        root.goPrev.visibility = View.VISIBLE

        select_big_image(root)
        root.goNext.setOnClickListener {
            if (screened_big_image < new_photo_pointer){ // 마지막 사진에서는 Next가 작동하지 않음
                screened_big_image += 1
                select_big_image(root)
            }
        }
        root.goPrev.setOnClickListener {
            if (screened_big_image > 1){ // 처음 사진에서는 Prev가 작동하지 않음
                screened_big_image -=1
                select_big_image(root)
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_gallery, container, false)


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
        if (new_photo_pointer >= 11) {
            root.imageButton11.setOnClickListener {
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







        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(): GalleryFragment {
            return GalleryFragment()
        }
    }
}