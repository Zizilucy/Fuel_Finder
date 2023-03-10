package com.example.fuelfinder

import android.content.Intent

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_wallet.*

class WalletFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bookingstextview = findViewById<TextView>(R.id.bookings)
        bookingstextview.setOnClickListener {
            val intent = Intent(this, bookings::class.java)
            startActivity(intent)

            bookings.movementMethod = LinkMovementMethod.getInstance();
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val referralbonustextview = findViewById<TextView>(R.id.bookings)
        referralbonustextview.setOnClickListener {
            val intent = Intent(this, referbonus2::class.java)
            startActivity(intent)

            referbonus2.movementMethod = LinkMovementMethod.getInstance();
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val LoyaltyPointstextview = findViewById<TextView>(R.id.bookings)
        LoyaltyPointstextview.setOnClickListener {
            val intent = Intent(this, loyaltypoint::class.java)
            startActivity(intent)

            loyaltypoint.movementMethod = LinkMovementMethod.getInstance();
        }
    }
}