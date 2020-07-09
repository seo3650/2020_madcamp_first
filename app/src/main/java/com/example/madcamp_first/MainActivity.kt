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


class MainActivity : AppCompatActivity() {
    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private var currentAnimator: Animator? = null

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private var shortAnimationDuration: Int = 0

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
            //zoomImageFromThumb(thumb1View, R.drawable.cloud)
        }

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
    }

    private fun zoomImageFromThumb(thumbView: View, imageResId: Int) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        currentAnimator?.cancel()

        // Load the high-resolution "zoomed-in" image.
        val expandedImageView: ImageView = findViewById(R.id.expanded_image)
        expandedImageView.setImageResource(imageResId)

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        val startBoundsInt = Rect()
        val finalBoundsInt = Rect()
        val globalOffset = Point()

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).
        thumbView.getGlobalVisibleRect(startBoundsInt)
        findViewById<View>(R.id.container)
            .getGlobalVisibleRect(finalBoundsInt, globalOffset)
        startBoundsInt.offset(-globalOffset.x, -globalOffset.y)
        finalBoundsInt.offset(-globalOffset.x, -globalOffset.y)

        val startBounds = RectF(startBoundsInt)
        val finalBounds = RectF(finalBoundsInt)

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).
        val startScale: Float
        if ((finalBounds.width() / finalBounds.height() > startBounds.width() / startBounds.height())) {
            // Extend start bounds horizontally
            startScale = startBounds.height() / finalBounds.height()
            val startWidth: Float = startScale * finalBounds.width()
            val deltaWidth: Float = (startWidth - startBounds.width()) / 2
            startBounds.left -= deltaWidth.toInt()
            startBounds.right += deltaWidth.toInt()
        } else {
            // Extend start bounds vertically
            startScale = startBounds.width() / finalBounds.width()
            val startHeight: Float = startScale * finalBounds.height()
            val deltaHeight: Float = (startHeight - startBounds.height()) / 2f
            startBounds.top -= deltaHeight.toInt()
            startBounds.bottom += deltaHeight.toInt()
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.alpha = 0f
        expandedImageView.visibility = VISIBLE

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.pivotX = 0f
        expandedImageView.pivotY = 0f

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        currentAnimator = AnimatorSet().apply {
            play(
                ObjectAnimator.ofFloat(
                    expandedImageView,
                    View.X,
                    startBounds.left,
                    finalBounds.left)
            ).apply {
                with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top, finalBounds.top))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f))
            }
            duration = shortAnimationDuration.toLong()
            interpolator = DecelerateInterpolator()
            addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator) {
                    currentAnimator = null
                }

                override fun onAnimationCancel(animation: Animator) {
                    currentAnimator = null
                }
            })
            start()
        }

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        expandedImageView.setOnClickListener {
            currentAnimator?.cancel()

            // Animate the four positioning/sizing properties in parallel,
            // back to their original values.
            currentAnimator = AnimatorSet().apply {
                play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left)).apply {
                    with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale))
                    with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale))
                }
                duration = shortAnimationDuration.toLong()
                interpolator = DecelerateInterpolator()
                addListener(object : AnimatorListenerAdapter() {

                    override fun onAnimationEnd(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = GONE
                        currentAnimator = null
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        thumbView.alpha = 1f
                        expandedImageView.visibility = GONE
                        currentAnimator = null
                    }
                })
                start()
            }
        }
    }

}


