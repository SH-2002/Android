package com.learning.ziachat

import android.content.Context
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.learning.ziachat.customviews.CustomImagePicker

class ImagePickingBottomSheet(context: Context) {

    private val bottomSheet : BottomSheetDialog = BottomSheetDialog(context)
    private var category1 : CustomImagePicker?
    private var category2 : CustomImagePicker?
    private var category3 : CustomImagePicker?
    private var category4 : CustomImagePicker?
    private var category5 : CustomImagePicker?
    private var category6 : CustomImagePicker?
    private val TAG = ImagePickingBottomSheet::class.java.simpleName

    init {
        bottomSheet.setContentView(R.layout.bottom_sheet_image_picker)
        bottomSheet.setCanceledOnTouchOutside(false)

        category1 = bottomSheet.findViewById(R.id.category1)
        category2 = bottomSheet.findViewById(R.id.category2)
        category3 = bottomSheet.findViewById(R.id.category3)
        category4 = bottomSheet.findViewById(R.id.category4)
        category5 = bottomSheet.findViewById(R.id.category5)
        category6 = bottomSheet.findViewById(R.id.category6)

        category1?.setOnClickListener {
            Log.e(TAG,category1.toString())
        }

        val submit = bottomSheet.findViewById<Button>(R.id.submit)
        submit?.setOnClickListener {
            Toast.makeText(context,"Bottom Sheet Cancelled",Toast.LENGTH_SHORT).show()
            bottomSheet.dismiss()
        }

    }

    fun show(){
        bottomSheet.show()
    }


    fun dismiss(){
        bottomSheet.dismiss()
    }



}