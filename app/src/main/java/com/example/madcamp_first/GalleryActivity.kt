package com.example.madcamp_first

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.Point
import android.graphics.Rect
import android.graphics.RectF
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.View.*
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import com.example.madcamp_first.ui.main.SectionsPagerAdapter


class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val thumb1View: View = findViewById(R.id.imageButton1)
        val thumb2View: View = findViewById(R.id.imageButton2)
        val thumb3View: View = findViewById(R.id.imageButton3)
        val thumb4View: View = findViewById(R.id.imageButton4)
        val thumb5View: View = findViewById(R.id.imageButton5)
        val thumb6View: View = findViewById(R.id.imageButton6)
        val thumb7View: View = findViewById(R.id.imageButton7)
        val thumb8View: View = findViewById(R.id.imageButton8)
        val thumb9View: View = findViewById(R.id.imageButton9)
        val thumb10View: View = findViewById(R.id.imageButton10)
        val thumb11View: View = findViewById(R.id.imageButton11)
        val thumb12View: View = findViewById(R.id.imageButton12)
        val thumb13View: View = findViewById(R.id.imageButton13)
        val thumb14View: View = findViewById(R.id.imageButton14)
        val thumb15View: View = findViewById(R.id.imageButton15)
        val thumb16View: View = findViewById(R.id.imageButton16)
        val thumb17View: View = findViewById(R.id.imageButton17)
        val thumb18View: View = findViewById(R.id.imageButton18)
        val thumb19View: View = findViewById(R.id.imageButton19)
        val thumb20View: View = findViewById(R.id.imageButton20)


        thumb1View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb2View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.cloud)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb3View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.cloud)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb4View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb5View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb6View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb7View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb8View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb9View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb10View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb11View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb12View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb13View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb14View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb15View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb16View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb17View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb18View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb19View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }
        thumb20View.setOnClickListener {
            val expandedImageView: ImageView = findViewById(R.id.expanded_image)
            val BackgroundImageView: ImageView = findViewById(R.id.Background)
            //val resource = resources.getResourceName(R.id.imageButton1)
            expandedImageView.setImageResource(R.drawable.barley)
            expandedImageView.visibility = VISIBLE
            BackgroundImageView.visibility = VISIBLE

            expandedImageView.setOnClickListener{
                expandedImageView.visibility = INVISIBLE
                BackgroundImageView.visibility = INVISIBLE
            }
        }
    }


}


