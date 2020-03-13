package com.learning.ziachat

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.learning.ziachat.customviews.CustomBottomSheet
import com.learning.ziachat.customviews.CustomImagePicker

class ImagePickingBottomSheet(private val context: Context, private val dimView : View) : CustomBottomSheet.OnBottomDismissed {

    private val bottomSheet: CustomBottomSheet = CustomBottomSheet(context,this)
    private var category1: CustomImagePicker?
    private var category2: CustomImagePicker?
    private var category3: CustomImagePicker?
    private var category4: CustomImagePicker?
    private var category5: CustomImagePicker?
    private var category6: CustomImagePicker?
    private var parent: ConstraintLayout?
    private val TAG = ImagePickingBottomSheet::class.java.simpleName
    private val selectedImages: MutableList<Drawable> = ArrayList()
    private val sendImages: SendImages = context as SendImages

    init {
        bottomSheet.setContentView(R.layout.bottom_sheet_image_picker)
        bottomSheet.setCanceledOnTouchOutside(false)

        category1 = bottomSheet.findViewById(R.id.category1)
        category2 = bottomSheet.findViewById(R.id.category2)
        category3 = bottomSheet.findViewById(R.id.category3)
        category4 = bottomSheet.findViewById(R.id.category4)
        category5 = bottomSheet.findViewById(R.id.category5)
        category6 = bottomSheet.findViewById(R.id.category6)
        parent = bottomSheet.findViewById(R.id.parent)

        category1?.setOnClickListener {
            counter(it)
        }

        category2?.setOnClickListener {
            counter(it)
        }

        category3?.setOnClickListener {
            counter(it)
        }

        category4?.setOnClickListener {
            counter(it)
        }

        category5?.setOnClickListener {
            counter(it)
        }

        category6?.setOnClickListener {
            counter(it)
        }

        val submit = bottomSheet.findViewById<Button>(R.id.submit)
        submit?.setOnClickListener {
            sendImages.sendImages(selectedImages)
            dismiss()
        }

    }

    fun getBottomSheet(): View {
        return parent as View
    }

    fun show() {
        bottomSheet.show()
    }



    private fun dismiss() {
        dimView.setBackgroundColor(ContextCompat.getColor(context,R.color.transparent))
        bottomSheet.dismiss()
    }

    private fun counter(v: View) {
        val customImagePicker = v as CustomImagePicker
        val image = customImagePicker.getImage()
        if (selectedImages.indexOf(image) != -1) {
            selectedImages.remove(image)
            Log.e(TAG, "Image removed! size = ${getSelectedCount()}")
        } else {
            if (getSelectedCount() >= 5) {
                Log.e(TAG, "Limit reached! size = ${getSelectedCount()}")
                Toast.makeText(context, "You can select upto 5 images!", Toast.LENGTH_SHORT).show()
                customImagePicker.radioChecker(false)
            } else {
                selectedImages.add(image)
                Log.e(TAG, "Image added! size = ${getSelectedCount()}")
            }
        }
    }

    private fun getSelectedCount(): Int {
        return selectedImages.size
    }

    interface SendImages {
        fun sendImages(images: MutableList<Drawable>)
    }

    override fun bottomSheetDismissed() {
        dimView.setBackgroundColor(ContextCompat.getColor(context,R.color.transparent))
    }

}