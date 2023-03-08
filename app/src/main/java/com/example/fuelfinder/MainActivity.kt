package com.example.fuelfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

lateinit var button : Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<Button>(R.id.btn_sign_in)
        button.setOnClickListener(this)
    }
    override fun onClick(view: View?){
    when(view?.id){
        R.id.btn_sign_in ->{
        }
        }
    } }





