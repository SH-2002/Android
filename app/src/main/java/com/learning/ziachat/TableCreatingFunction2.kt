package com.learning.ziachat

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.learning.ziachat.dataclasses.UserInformation


class TableCreatingFunction2(
    private val context: Context,
    private val userInformations: List<UserInformation>
) {

    private val onTableClicked = context as OnTableClicked

    private val keys =
        arrayOf("Id", "Name", "Username", "Email", "Phone", "Website", "Company", "Address")

    fun viewReturner(toSetHeight: Boolean = false): ScrollView {

        val scrollLayout = ScrollView(context)
        val scrollParams =
            if (!toSetHeight) {
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
            } else {
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, 546)
            }
        scrollLayout.layoutParams = scrollParams
        val layout = LinearLayout(context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layout.layoutParams = params
        layout.orientation = LinearLayout.VERTICAL
        layout.id = View.generateViewId()
        layout.addView(createTable(userInformations))
        layout.setOnClickListener {
            onTableClicked.sendTable(userInformations)
        }
        scrollLayout.addView(layout)
        return scrollLayout
    }


    private fun createTable(table: List<UserInformation>): TableLayout {
        val layout = TableLayout(context)
        val params = TableLayout.LayoutParams(
            TableLayout.LayoutParams.WRAP_CONTENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        layout.layoutParams = params
        layout.id = View.generateViewId()
        layout.addView(headerCreator())
        layout.getChildAt(0).setBackgroundColor(ContextCompat.getColor(context, R.color.gray_op20))
        table.forEach {
            layout.addView(createRows(it))
        }
        layout.background = ContextCompat.getDrawable(
            context,
            R.drawable.rounded_corner
        )
        return layout
    }

    private fun headerCreator(): TableRow {
        val row = TableRow(context)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        row.layoutParams = params
        row.id = View.generateViewId()
        keys.forEach {
            row.addView(createTextViews(it, true))
        }
        return row
    }

    private fun createRows(rows: UserInformation): TableRow {
        val row = TableRow(context)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(16)
        row.layoutParams = params
        row.id = View.generateViewId()
        row.addView(createTextViews(rows.id.toString()))
        row.addView(createTextViews(rows.name))
        row.addView(createTextViews(rows.username))
        row.addView(createTextViews(rows.email))
        row.addView(createTextViews(rows.phone))
        row.addView(createTextViews(rows.website))
        row.addView(createTextViews(rows.company.name))
        row.addView(createTextViews("${rows.address.street} , ${rows.address.suite} , ${rows.address.city} - ${rows.address.zipcode}"))
        return row
    }

    private fun createTextViews(information: String, isHead: Boolean = false): TextView {
        val textView = TextView(context)
        textView.id = View.generateViewId()
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(32, 16, 48, 16)
        textView.layoutParams = params
        textView.maxWidth = 700
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
        if (isHead) {
            textView.textSize = 24F
        }
        textView.text = information
        return textView
    }

    interface OnTableClicked {
        fun sendTable(tableData: List<UserInformation>)
    }

}