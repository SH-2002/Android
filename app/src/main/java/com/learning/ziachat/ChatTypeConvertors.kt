package com.learning.ziachat

import android.graphics.drawable.Drawable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ChatTypeConverters {

    companion object {
        fun listToString(value: MutableList<Array<String>>?): String? {
            return if (value == null) null else Gson().toJson(value)
        }


        fun stringToList(value: String?): MutableList<Array<String>>? {
            return (if (value == null) null else Gson().fromJson(
                value,
                object : TypeToken<MutableList<Array<String>>>() {}.type
            ))
        }

        fun arrayToString(value: Array<Drawable>?): String? {
            return if (value == null) null else Gson().toJson(value)
        }


        fun stringToArray(value: String?): Array<Drawable>? {
            return (if (value == null) null else Gson().fromJson(
                value,
                object : TypeToken<Array<Drawable>>() {}.type
            ))
        }

    }

}