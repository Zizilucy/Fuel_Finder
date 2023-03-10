package com.example.fuelfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.bottom_navigation.*

abstract class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.btn_log_in)
        button.setOnClickListener {
            val intent = Intent(this, bottomNavigationView::class.java)
            startActivity(intent)
        }
    }
}





