package com.learning.ziachat.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.ziachat.ChatTypeConverters
import com.learning.ziachat.DataClass
import com.learning.ziachat.ImagePickingBottomSheet
import com.learning.ziachat.R
import com.learning.ziachat.adapters.ChatAdapter
import com.learning.ziachat.adapters.ImagePagerAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import java.io.ByteArrayOutputStream


class ChatActivity : AppCompatActivity(), ChatAdapter.OnAcceptClicked,
    ImagePickingBottomSheet.SendImages,
    ChatAdapter.OnDetailsPageSender{


    private val messageList: MutableList<Any> = ArrayList()
    private val TAG = ChatActivity::class.java.simpleName
    private val tableArray: MutableList<Array<String>> = ArrayList()
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var parent: RelativeLayout
    private lateinit var dimView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        arrayAdder()
        val window = this.window
        parent = findViewById(R.id.parent)
        dimView = findViewById(R.id.dimView)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(
            this,
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


    private fun arrayAdder() {
        tableArray.add(arrayOf("S.no", "Name", "Employee Id", "Email"))
        tableArray.add(arrayOf("1", "Seenivasan", "ZUCH810", "seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("2", "Priya", "ZUCH800", "priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("3", "Seenivasan", "ZUCH810", "seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("4", "Priya", "ZUCH800", "priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("5", "Seenivasan", "ZUCH810", "seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("6", "Priya", "ZUCH800", "priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("7", "Seenivasan", "ZUCH810", "seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("8", "Priya", "ZUCH800", "priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("9", "Seenivasan", "ZUCH810", "seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("10", "Priya", "ZUCH800", "priya.eg@zohocorp.com"))
        tableArray.add(arrayOf("11", "Seenivasan", "ZUCH810", "seenivasan.t@zohocorp.com"))
        tableArray.add(arrayOf("12", "Priya", "ZUCH800", "priya.eg@zohocorp.com"))
    }

    override fun onAcceptClicked() {
        val bottomSheetDialog = ImagePickingBottomSheet(this, dimView)
        dimView.setBackgroundColor(ContextCompat.getColor(this, R.color.black_op))
        bottomSheetDialog.show()
    }

    override fun sendImages(images: MutableList<Drawable>) {
        val imageArr: Array<Drawable> =
            Array(images.size) { ContextCompat.getDrawable(this, R.drawable.laptop)!! }
        images.forEach {
            imageArr[images.indexOf(it)] = it
        }
        messageList.add(imageArr)
        chatAdapter.setMessages(messageList)
    }

    override fun sendData(data: Any) {
        val intent = Intent(this, DetailsActivity::class.java)

        val bundle = Bundle()
        when (data) {
            is MutableList<*> -> {
                bundle.putSerializable("data",DataClass(1,data as MutableList<Array<String>>,null))
            }
            is Array<*> -> {
                Log.e(TAG,data.toString())
                bundle.putSerializable("data",DataClass(2,null,data as Array<ByteArray>))
            }
        }
        intent.putExtra("data",bundle)
        startActivity(intent)
    }

//    override fun onClicked(data: Array<Drawable>) {
//        val intent = Intent(this, DetailsActivity::class.java)
//        val bundle = Bundle()
//        bundle.putSerializable("data",DataClass(2,null,typeConverter(data)))
//        intent.putExtra("data",bundle)
//        startActivity(intent)
//    }
//
//    private fun typeConverter(images: Array<Drawable>): Array<ByteArray> {
//        val imagesArray: Array<ByteArray> =
//            Array(images.size) { i -> ByteArrayOutputStream().toByteArray() }
//        images.forEach {
//            val bitmap = (it as BitmapDrawable).bitmap
//            val stream = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 65, stream)
//            val bitmapData = stream.toByteArray()
//            imagesArray[images.indexOf(it)] = bitmapData
//        }
//        return imagesArray
//    }

}
