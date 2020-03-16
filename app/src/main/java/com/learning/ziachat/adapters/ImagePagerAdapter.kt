package com.learning.ziachat.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter

class ImagePagerAdapter(
    private val context: Context,
    private val thisValue: OnClicked?,
    private var images: Array<Drawable>,
    private val previous: ImageView?,
    private val next: ImageView?
) :
    PagerAdapter() {

    private val TAG = ImagePagerAdapter::class.java.simpleName
    private val listener: OnClicked? = thisValue

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (images.size > 1) {
            arrowController(position)
        }
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        imageView.setOnClickListener { listener?.onClicked(images) }
        imageView.setImageDrawable(images[position])
        container.addView(imageView, 0)
        return imageView
    }

    private fun arrowController(position: Int) {
        when (position) {
            0 -> {
                previous?.visibility = View.GONE
                next?.visibility = View.VISIBLE
            }
            (images.size - 1) -> {
                next?.visibility = View.GONE
                previous?.visibility = View.VISIBLE
            }
            else -> {
                next?.visibility = View.VISIBLE
                previous?.visibility = View.VISIBLE
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    interface OnClicked {
        fun onClicked(data: Array<Drawable>)
    }

}