package com.learning.ziachat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.response_ui.*

class ChatActivity : AppCompatActivity() {

    private val messageList : MutableList<Any> = ArrayList()
    private val TAG = ChatActivity::class.java.simpleName
    private val tableArray : MutableList<Array<String>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        arrayAdder()
        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,R.color.chatBackground)
        messageList.add("Hai")
        messageList.add("SeeNu")
        messageList.add(tableArray)
        messageList.add("Good Evening!")
        chatView.layoutManager = LinearLayoutManager(this)
        chatView.adapter = ChatAdapter(this,messageList)
    }




    private fun arrayAdder(){
        tableArray.add(arrayOf("S.no","Name","Employee Id","Email"))
        tableArray.add(arrayOf("1","Seenivasan","ZUCH810","seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("2","Priya","ZUCH800","priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("3","Seenivasan","ZUCH810","seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("4","Priya","ZUCH800","priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("5","Seenivasan","ZUCH810","seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("6","Priya","ZUCH800","priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("7","Seenivasan","ZUCH810","seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("8","Priya","ZUCH800","priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("9","Seenivasan","ZUCH810","seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("10","Priya","ZUCH800","priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("11","Seenivasan","ZUCH810","seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("12","Priya","ZUCH800","priya.eg@zohocorp.com"))
    }
}
