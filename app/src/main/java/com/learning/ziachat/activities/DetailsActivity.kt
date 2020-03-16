package com.learning.ziachat.activities

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.learning.ziachat.DataClass
import com.learning.ziachat.R
import com.learning.ziachat.adapters.ChatAdapter
import com.learning.ziachat.adapters.ImagePagerAdapter
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), TableCreatingFunctions.OnClicked {

    private val TAG = DetailsActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(
            this,
            R.color.chatBackground
        )

        var data1: MutableList<Array<String>>?
        var data2: Array<ByteArray>? = null

        val bundle: Bundle? = intent.getBundleExtra("data")
        val intent: DataClass? = bundle!!.getSerializable("data") as DataClass
        intent?.let {
            Log.e(TAG, "Key = ${intent.key}")
            when (intent.key) {
                1 -> {
                    parentLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.respondMsgColor))
                    heading.text = "Table"
                    scrollLayout.visibility = View.VISIBLE
                    subParent.visibility = View.GONE
                    data1 = intent.table
                    val layout = TableCreatingFunctions(this).headerSeparator(data1!!,false)
                    Log.e(TAG,data1.toString())
                    scrollLayout.addView(layout)
                    Log.e(TAG,layout.toString())
                }
                2 -> {
                    heading.text = "Images"
                    parentLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.chatBackground))
                    scrollLayout.visibility = View.GONE
                    subParent.visibility = View.VISIBLE
                    data2 = intent.images
                    Log.e(TAG,intent.images.toString())
                    Log.e(TAG, "Came")
                    val adapter = ImagePagerAdapter(this,null,convertToImage(data2!!),previous,next)
                    detailsImageViewPager.adapter = adapter
                    Log.e(TAG,data2.toString())
                }
                else -> {

                }
            }
        }


    }

    private fun convertToImage(images : Array<ByteArray>) : Array<Drawable>{
        val imagesArray : Array<Drawable> = Array(images.size){i -> ContextCompat.getDrawable(this,R.drawable.laptop)!!}
        images.forEach {
            val bitmap = BitmapFactory.decodeByteArray(it,0,it.size)
            val drawable : Drawable = BitmapDrawable(resources,bitmap)
            imagesArray[images.indexOf(it)] = drawable
        }
        return imagesArray
    }

    override fun sendData(data: MutableList<Array<String>>) {

    }


}
