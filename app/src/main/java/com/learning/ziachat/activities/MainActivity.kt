package com.learning.ziachat.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.ziachat.R

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        
        withoutRecycler.setOnClickListener {
            startActivity(Intent(this,
                ChatActivity2::class.java))
        }

        withRecycler.setOnClickListener {
            startActivity(Intent(this,
                ChatActivity::class.java))
        }
    }

}
