package com.learning.ziachat

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import com.learning.ziachat.dataclasses.UserInformation
import java.io.Serializable

class DataClass(
    var key : Int = 0,
    var images : Array<ByteArray>? = null
) : Serializable