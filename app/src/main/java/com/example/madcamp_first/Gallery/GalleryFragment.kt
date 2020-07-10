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

        fun backtothumbnail() { // Listen for going out to the thumbnails screen
            root.imageButton21.setOnClickListener {
                root.scrollView1.visibility = View.VISIBLE
                root.scrollView2.visibility = View.INVISIBLE
            }
            root.imageButton22.setOnClickListener {
                root.scrollView1.visibility = View.VISIBLE
                root.scrollView2.visibility = View.INVISIBLE
            }
            root.imageButton23.setOnClickListener {
                root.scrollView1.visibility = View.VISIBLE
                root.scrollView2.visibility = View.INVISIBLE
            }
            root.imageButton24.setOnClickListener {
                root.scrollView1.visibility = View.VISIBLE
                root.scrollView2.visibility = View.INVISIBLE
            }
        }


        root.imageButton1.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }

        root.imageButton2.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton3.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton4.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton5.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton6.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton7.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton8.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton9.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton10.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton11.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton12.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton13.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton14.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton15.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton16.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton17.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton18.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton19.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
        }
        root.imageButton20.setOnClickListener {
            root.scrollView1.visibility = View.INVISIBLE
            root.scrollView2.visibility = View.VISIBLE
            backtothumbnail()
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