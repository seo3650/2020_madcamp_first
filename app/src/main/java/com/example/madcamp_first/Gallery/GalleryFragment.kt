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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_gallery, container, false)
        var i = 1

        fun select_big_image(){
            if (i == 1) {
                root.Bigscreen.setImageResource(R.drawable.barley)
            }
            if (i == 2) {
                root.Bigscreen.setImageResource(R.drawable.cloud)
            }
            if (i == 3) {
                root.Bigscreen.setImageResource(R.drawable.barley)
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