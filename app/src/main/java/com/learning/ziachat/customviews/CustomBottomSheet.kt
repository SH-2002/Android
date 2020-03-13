package com.learning.ziachat.customviews

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.learning.ziachat.ImagePickingBottomSheet

class CustomBottomSheet(context: Context, pickerContext: ImagePickingBottomSheet) : BottomSheetDialog(context) {

    private val TAG = CustomBottomSheet::class.java.simpleName
    private val onBottomDismissed : OnBottomDismissed = pickerContext

    override fun dismiss() {
        onBottomDismissed.bottomSheetDismissed()
        super.dismiss()
    }

    interface OnBottomDismissed{
        fun bottomSheetDismissed()
    }

}