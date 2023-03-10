package com.example.fuelfinder

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import android.text.method.LinkMovementMethod
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class welcome_page2 : AppCompatActivity(){
    val ss = SpannableString("Already have an account? Log in")
    var clickableSpan: ClickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            startActivity(Intent(this@welcome_page2, MainActivity::class.java))
        }

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)
            ds.setColor(Color.RED)
            ds.isUnderlineText = false

            ss.setSpan(clickableSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val boldSpan by lazy { StyleSpan(Typeface.BOLD) }
            ss.setSpan(boldSpan, 23, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            val textView = findViewById<TextView>(R.id.haveaccount)
            textView.text = ss
            textView.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page2)
        val button: Button = findViewById(R.id.get_started_button)
        button.setOnClickListener {
            val intent = Intent(this, GetStarted::class.java)
            startActivity(intent)
        }
    }
}

