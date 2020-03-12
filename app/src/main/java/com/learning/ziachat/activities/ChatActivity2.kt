package com.learning.ziachat.activities

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.learning.ziachat.R
import com.learning.ziachat.TableSize
import kotlinx.android.synthetic.main.user_chat_ui.view.*

class ChatActivity2 : AppCompatActivity() {

    private val messageList : MutableList<Any> = ArrayList()
    private val TAG = ChatActivity::class.java.simpleName
    private val tableArray : MutableList<Array<String>> = ArrayList()
    private lateinit var chatView : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat2)
        arrayAdder()
        chatView = findViewById(R.id.chatView2)
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
        checker(messageList)
    }


    private fun checker(messages: MutableList<Any>){
        val linearLayout = LinearLayout(this)
        linearLayout.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        linearLayout.orientation = LinearLayout.VERTICAL
        messages.forEach {
            if(it is String){
                val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(8)
                val view = LayoutInflater.from(this).inflate(R.layout.user_chat_ui,null)
                view.userMsg.text = it
                view.layoutParams = params
                linearLayout.addView(view)
            }else{
                val params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT,ConstraintLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(8)
                val view = LayoutInflater.from(this).inflate(R.layout.response_ui,null)
                view.layoutParams = params
                val layout = view.findViewById<HorizontalScrollView>(R.id.scrollLayout)
                layout.addView(headerSeparator(it as MutableList<Array<String>>))
                linearLayout.addView(view)
            }
        }
        chatView.addView(linearLayout)
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

    private fun headerSeparator(messages: MutableList<Array<String>>) : LinearLayout {
        val parentLayout = LinearLayout(this)
        parentLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        parentLayout.addView(createTable(messages.subList(0,1)))
        parentLayout.orientation = LinearLayout.VERTICAL
        val messageWithOutHead = messages.subList(1,messages.size)
        val scrollView = ScrollView(this)
        scrollView.id = View.generateViewId()
        val params : LinearLayout.LayoutParams
        params = if(messageWithOutHead.size > 6){
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,742)
        }else{
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        }
        Log.e(TAG,"Scroll view id = ${scrollView.id}")
        scrollView.layoutParams = params
        scrollView.addView(createTable(messageWithOutHead))
        parentLayout.addView(scrollView)
        return parentLayout
    }

    private fun createTable(messages: MutableList<Array<String>>) : TableLayout {
        val params = TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT)
        val layout : TableLayout = createRows(messages)
        layout.layoutParams = params
        layout.background = ContextCompat.getDrawable(this,
            R.drawable.rounded_corner
        )
        layout.id = View.generateViewId()
        return layout
    }

    private fun createRows(table : MutableList<Array<String>>) : TableLayout {
        val tableLayout = TableLayout(this)
        val params = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
        params.setMargins(16)
        table.forEach {
            val row : TableRow = createTextViews(it,
                TableSize.isFirst
            )
            row.id = View.generateViewId()
            row.layoutParams = params
            if(TableSize.isFirst){
                row.setBackgroundColor(ContextCompat.getColor(this,
                    R.color.gray_op20
                ))
            }
            TableSize.isFirst = false
            tableLayout.addView(row)
        }
        return tableLayout
    }

    private fun createTextViews(message : Array<String>,isFirst : Boolean) : TableRow {
        val row = TableRow(this)
        val params = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
        params.setMargins(32,16,48,16)
        message.forEach {
            val textView = TextView(this)
            textView.layoutParams = params
            textView.id = View.generateViewId()
            textView.text = it
            if(isFirst){
                textView.textSize = 18F
            }
            textView.setPadding(8)
            textView.setTextColor(ContextCompat.getColor(this,
                R.color.white
            ))
            textView.typeface =
                Typeface.create(ResourcesCompat.getFont(this,
                    R.font.roboto_slab
                ), Typeface.NORMAL)
            row.addView(textView)
        }
        return row
    }

}
