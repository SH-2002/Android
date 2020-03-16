package com.learning.ziachat

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class DataClass(
    var key : Int = 0,
    var table : MutableList<Array<String>>? = null,
    var images : Array<ByteArray>? = null
) : Serializable