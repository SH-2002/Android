package com.learning.ziachat.activities

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.learning.ziachat.ImagePickingBottomSheet
import com.learning.ziachat.adapters.ChatAdapter
import com.learning.ziachat.R
import com.learning.ziachat.customviews.CustomImagePicker
import kotlinx.android.synthetic.main.activity_chat.*
import kotlin.reflect.typeOf

class ChatActivity : AppCompatActivity(), ChatAdapter.OnAcceptClicked, ImagePickingBottomSheet.SendImages {

    private val messageList : MutableList<Any> = ArrayList()
    private val TAG = ChatActivity::class.java.simpleName
    private val tableArray : MutableList<Array<String>> = ArrayList()
    private lateinit var chatAdapter: ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        arrayAdder()
        val window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,
            R.color.chatBackground
        )
        messageList.add("Hai")
        messageList.add("SeeNu")
        messageList.add(tableArray)
        messageList.add("Good Evening!")
        chatAdapter = ChatAdapter(this, messageList)
        chatView.layoutManager = LinearLayoutManager(this)
        chatView.adapter = chatAdapter
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

    override fun onAcceptClicked() {
//        val bottomSheet = BottomSheetDialog(this)
//        bottomSheet.setContentView(R.layout.bottom_sheet_image_picker)
//        bottomSheet.setCanceledOnTouchOutside(false)
//
//        val submit = bottomSheet.findViewById<Button>(R.id.submit)
//        submit?.setOnClickListener {
//            Toast.makeText(this,"Bottom Sheet Cancelled",Toast.LENGTH_SHORT).show()
//            bottomSheet.dismiss() }
//
//        bottomSheet.show()
//
//        val category1 : CustomImagePicker? = bottomSheet.findViewById(R.id.category1)
//        category1?.setOnClickListener {
//            bottomSheet.dismiss()
//        }
        val bottomSheetDialog = ImagePickingBottomSheet(this)
        bottomSheetDialog.show()
    }

    override fun sendImages(images: MutableList<Drawable>) {
        chatAdapter.setMessages(messageList)
        images.forEach {
            messageList.add(it)
        }
        messageList.forEach {
            Log.e(TAG,it.toString())
        }
    }


}
