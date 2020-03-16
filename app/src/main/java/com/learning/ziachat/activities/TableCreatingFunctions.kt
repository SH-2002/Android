package com.learning.ziachat.activities

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.learning.ziachat.R

class TableCreatingFunctions(private val context: Context) {

    private val TAG = TableCreatingFunctions::class.java.simpleName
    private val listener: OnClicked = context as OnClicked
    private var isFirst: Boolean = true

    fun headerSeparator(messages: MutableList<Array<String>>, setListener: Boolean): LinearLayout {
        val parentLayout = LinearLayout(context)
        parentLayout.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        parentLayout.addView(createTable(messages.subList(0, 1), setListener))
        parentLayout.orientation = LinearLayout.VERTICAL
        val messageWithOutHead = messages.subList(1, messages.size)
        val scrollView = ScrollView(context)
        scrollView.id = View.generateViewId()
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        Log.e(TAG, "Scroll view id = ${scrollView.id}")
        scrollView.layoutParams = params
        scrollView.addView(createTable(messageWithOutHead, setListener))
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
                listener.sendData(messages)
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
                isFirst
            )
            row.id = View.generateViewId()
            row.layoutParams = params
            if (isFirst) {
                row.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.gray_op20
                    )
                )
            }
            isFirst = false
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

    interface OnClicked {
        fun sendData(data: MutableList<Array<String>>)
    }

}