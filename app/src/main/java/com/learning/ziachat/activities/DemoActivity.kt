package com.learning.ziachat.activities

import android.app.Activity
import android.os.Bundle
import com.learning.ziachat.R

class DemoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottom_sheet_image_picker)
    }

}