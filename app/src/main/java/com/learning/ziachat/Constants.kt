package com.learning.ziachat

import com.learning.ziachat.dataclasses.UserInformation

class TableSize{

    companion object{
        var tableSize = 0
        var isFirst = true
    }

}

class ScreenSize{

    companion object{
        var width : Int = 0
        var height : Int = 0
    }

}

class UserInformation{
    companion object{
        var userInformation : List<UserInformation>? = null
    }
}