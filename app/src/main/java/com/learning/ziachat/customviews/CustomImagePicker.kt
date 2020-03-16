package com.learning.ziachat.customviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RelativeLayout
import android.widget.TextView
import com.learning.ziachat.R

class CustomImagePicker(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val isSelected: RadioButton
    private var isImageIsSelected = false
    private val imageView: ImageView

    init {
        View.inflate(context, R.layout.custom_imagepicker_view, this)
        val parent: RelativeLayout = findViewById(R.id.parent)
        imageView = findViewById(R.id.image)
        val category: TextView = findViewById(R.id.category)
        isSelected = findViewById(R.id.isSelected)

        isSelected.setOnClickListener { isSelectedChanger() }

        parent.setOnClickListener {
            isSelectedChanger()
            performClick()
        }


        val attributes = context.obtainStyledAttributes(attrs, R.styleable.imagePickerView)
        imageView.setImageDrawable(attributes.getDrawable(R.styleable.imagePickerView_image))
        category.text = attributes.getText(R.styleable.imagePickerView_text)
        attributes.recycle()

    }

    fun radioChecker(isChecked: Boolean) {
        isSelected.isChecked = isChecked
        isImageIsSelected = isChecked
    }

    fun isImageSelected(): Boolean {
        return isSelected.isChecked
    }

    private fun isSelectedChanger() {
        if (!isImageIsSelected) {
            isSelected.isChecked = true
            isImageIsSelected = true
        } else {
            isSelected.isChecked = false
            isImageIsSelected = false
        }
    }

    fun getImage(): Drawable {
        return imageView.drawable
    }

}