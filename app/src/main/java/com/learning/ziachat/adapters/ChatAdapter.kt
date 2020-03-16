package com.learning.ziachat.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.learning.ziachat.R
import com.learning.ziachat.TableSize
import java.io.ByteArrayOutputStream

class ChatAdapter(
    private val context: Context,
    private var messages: MutableList<Any>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ImagePagerAdapter.OnClicked {

    private var tableData: MutableList<Array<String>>? = null

    private val TAG = ChatAdapter::class.java.simpleName
    private var scrollViewId: Int = 0
    private var onAcceptClicked: OnAcceptClicked = context as OnAcceptClicked
    private var onSendData: OnDetailsPageSender = context as OnDetailsPageSender

    override fun getItemViewType(position: Int): Int {
        return when {
            messages[position] is String -> 4
            messages[position] is Array<*> -> 8
            else -> 16
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            4 -> {
                view = LayoutInflater.from(context).inflate(R.layout.user_chat_ui, parent, false)
                UserMessageViewHolder(view)
            }
            8 -> {
                view = LayoutInflater.from(context).inflate(R.layout.chat_image_view, parent, false)
                return ImageViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(context).inflate(R.layout.response_ui, parent, false)
                ResponseMessageViewHolder(
                    view
                )
            }
        }
    }

    private fun typeConverter(images: Array<Drawable>): Array<ByteArray> {
        val imagesArray: Array<ByteArray> =
            Array(images.size) { i -> ByteArrayOutputStream().toByteArray() }
        images.forEach {
            val bitmap = (it as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 65, stream)
            val bitmapData = stream.toByteArray()
            imagesArray[images.indexOf(it)] = bitmapData
        }
        return imagesArray
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            messages[position] is String -> {
                val viewHolder = holder as UserMessageViewHolder
                viewHolder.userMsg.text = messages[position].toString()
            }
            messages[position] is Array<*> -> {
                Log.e(TAG, "Drawable came Position = $position")
                val viewHolder = holder as ImageViewHolder
                Log.e(TAG, messages[position].toString() + " position = $position")
                val imageAdapter = ImagePagerAdapter(
                    context,this,
                    messages[position] as Array<Drawable>,
                    holder.previous,
                    holder.next
                )
                if ((messages[position] as Array<Drawable>).size <= 1) {
                    holder.next.visibility = View.GONE
                    holder.previous.visibility = View.GONE
                }
                viewHolder.subParent.setOnClickListener { viewView(it) }
                viewHolder.imageView.adapter = imageAdapter
                viewHolder.imageView.setOnClickListener {
                    viewView(it)
                    onSendData.sendData(typeConverter(messages[position] as Array<Drawable>)) }
            }
            else -> {
                tableData = messages[position] as MutableList<Array<String>>
                val viewHolder = holder as ResponseMessageViewHolder
                Log.e(TAG, messages[position].toString())
                val message = messages[position] as MutableList<Array<String>>
                viewHolder.accept.setOnClickListener {
                    onAcceptClicked.onAcceptClicked()
                }
                viewHolder.layout.removeAllViews()
                val view: LinearLayout = headerSeparator(message,true)
                viewHolder.layout.addView(view)

            }
        }
    }

    private fun viewView(v: View) {
        Log.e(TAG, v.toString())
    }

    class UserMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userMsg: TextView = itemView.findViewById(R.id.userMsg)
    }

    class ResponseMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: HorizontalScrollView = itemView.findViewById(R.id.scrollLayout)
        val parent: ConstraintLayout = itemView.findViewById(R.id.parent)
        val accept: Button = itemView.findViewById(R.id.accept)
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ViewPager = itemView.findViewById(R.id.imagePager)
        val parent: RelativeLayout = itemView.findViewById(R.id.parent)
        val subParent: RelativeLayout = itemView.findViewById(R.id.subParent)
        val previous: ImageView = itemView.findViewById(R.id.previous)
        val next: ImageView = itemView.findViewById(R.id.next)
    }

    private fun getHeight(v: View) {
        Log.e(TAG, v.height.toString() + "  $v")
    }

    fun headerSeparator(messages: MutableList<Array<String>>,setListener: Boolean): LinearLayout {
        val parentLayout = LinearLayout(context)
        parentLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        parentLayout.addView(createTable(messages.subList(0, 1),setListener))
        parentLayout.orientation = LinearLayout.VERTICAL
        val messageWithOutHead = messages.subList(1, messages.size)
        val scrollView = ScrollView(context)
        scrollView.id = View.generateViewId()
        scrollViewId = scrollView.id
        val params: LinearLayout.LayoutParams
        params = if (messageWithOutHead.size > 6) {
            LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 742)
        } else {
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
        Log.e(TAG, "Scroll view id = ${scrollView.id}")
        scrollView.layoutParams = params
        scrollView.addView(createTable(messageWithOutHead,setListener))
        parentLayout.addView(scrollView)
        return parentLayout
    }

    private fun createTable(
        messages: MutableList<Array<String>>,
        setListener: Boolean
    ): TableLayout {
        val params = TableLayout.LayoutParams(
            TableLayout.LayoutParams.WRAP_CONTENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val layout: TableLayout = createRows(messages)
        if (setListener) {
            layout.setOnClickListener {
                onSendData.sendData(tableData as Any)
                viewView(it)
            }
        }
        layout.layoutParams = params
        layout.background = ContextCompat.getDrawable(
            context,
            R.drawable.rounded_corner
        )
        layout.id = View.generateViewId()
        return layout
    }

    private fun createRows(table: MutableList<Array<String>>): TableLayout {
        val tableLayout = TableLayout(context)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16)
        table.forEach {
            val row: TableRow = createTextViews(
                it,
                TableSize.isFirst
            )
            row.id = View.generateViewId()
            row.layoutParams = params
            if (TableSize.isFirst) {
                row.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.gray_op20
                    )
                )
            }
            TableSize.isFirst = false
            tableLayout.addView(row)
        }
        return tableLayout
    }

    private fun createTextViews(message: Array<String>, isFirst: Boolean): TableRow {
        val row = TableRow(context)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT
        )
        params.setMargins(32, 16, 48, 16)
        message.forEach {
            val textView = TextView(context)
            textView.layoutParams = params
            textView.id = View.generateViewId()
            textView.text = it
            if (isFirst) {
                textView.textSize = 18F
            }
            textView.setPadding(8)
            textView.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
            textView.typeface =
                Typeface.create(
                    ResourcesCompat.getFont(
                        context,
                        R.font.roboto_slab
                    ), Typeface.NORMAL
                )
            row.addView(textView)
        }
        return row
    }

    fun setMessages(messages: MutableList<Any>) {
        this.messages = messages
        notifyDataSetChanged()
    }

    interface OnAcceptClicked {
        fun onAcceptClicked()
    }

    interface OnDetailsPageSender {
        fun sendData(data: Any)
    }

    override fun onClicked(data: Array<Drawable>) {
        onSendData.sendData(typeConverter(data))
    }

}