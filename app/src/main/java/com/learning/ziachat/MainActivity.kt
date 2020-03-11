package com.learning.ziachat

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        withoutRecycler.setOnClickListener {
            startActivity(Intent(this,ChatActivity2::class.java))
        }

        withRecycler.setOnClickListener {
            startActivity(Intent(this,ChatActivity::class.java))
        }
    }

}
