package com.example.fuelfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Bookings: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bookings)
        val button: Button = findViewById(R.id.get_reward_btn)
        button.setOnClickListener {
            val intent = Intent(this, Bookings2::class.java)
            startActivity(intent)
        }
    }
}