package com.example.madcamp_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.madcamp_first.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
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

        root.imageButton1.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.barley)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton2.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton3.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton4.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton5.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton6.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton7.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton8.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton9.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton10.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton11.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton12.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton13.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton14.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton15.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton16.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton17.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton18.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton19.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
            }
        }
        root.imageButton20.setOnClickListener {
            root.expanded_image.setImageResource(R.drawable.cloud)
            root.expanded_image.visibility = View.VISIBLE
            root.Background.visibility = View.VISIBLE

            root.expanded_image.setOnClickListener{
                root.expanded_image.visibility = View.INVISIBLE
                root.Background.visibility = View.INVISIBLE
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