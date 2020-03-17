package com.learning.ziachat.activities

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.learning.ziachat.DataClass
import com.learning.ziachat.ImagePickingBottomSheet
import com.learning.ziachat.R
import com.learning.ziachat.TableCreatingFunction2
import com.learning.ziachat.adapters.ChatAdapter
import com.learning.ziachat.dataclasses.UserInformation
import kotlinx.android.synthetic.main.activity_chat.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ChatActivity : AppCompatActivity(), ChatAdapter.OnAcceptClicked,
    ImagePickingBottomSheet.SendImages,
    ChatAdapter.OnDetailsPageSender,
TableCreatingFunction2.OnTableClicked{


    private val messageList: MutableList<Any> = ArrayList()
    private val TAG = ChatActivity::class.java.simpleName
    private val tableArray: MutableList<Array<String>> = ArrayList()
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var parent: RelativeLayout
    private lateinit var dimView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
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
        messageList.add("Good Evening!")
        chatAdapter = ChatAdapter(this, messageList)
        chatView.layoutManager = LinearLayoutManager(this)
        chatView.adapter = chatAdapter
        apiCall()
    }

    private fun apiCall() {
        val retrofit = Retrofit.Builder()
            .baseUrl(UserInformation.ApiCall.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val userInformation = retrofit.create(UserInformation.ApiCall::class.java)

        val call = userInformation.getInformation()
        call.enqueue(object : Callback<List<UserInformation>> {
            override fun onFailure(call: Call<List<UserInformation>>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "API Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<List<UserInformation>>,
                response: Response<List<UserInformation>>
            ) {
                if (response.isSuccessful) {
                    if (!response.body().isNullOrEmpty()) {
                        messageList.add(response.body() as List<UserInformation>)
                        com.learning.ziachat.UserInformation.userInformation = response.body() as List<UserInformation>
                        chatAdapter.setMessages(messageList)
                    }
                }
            }

        })

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
            is List<*> -> {
                bundle.putSerializable(
                    "data",
                    DataClass(1,null)
                )
            }
            is Array<*> -> {
                Log.e(TAG, data.toString())
                bundle.putSerializable("data", DataClass(2, data as Array<ByteArray>))
            }
        }
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    override fun sendTable(tableData: List<UserInformation>) {
        sendData(tableData)
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
