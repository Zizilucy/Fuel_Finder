package com.example.fuelfinder

import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.view.Display
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import android.widget.*
import androidmads.library.qrgenerator.QRGContents
import androidmads.library.qrgenerator.QRGContents
import androidx.appcompat.app.AppCompatActivity

class LoyaltyPoints : AppCompatActivity() {
    lateinit var qrIV: ImageView
    lateinit var msgEdt: EditText
    lateinit var generateQRBtn: Button

    lateinit var bitmap: Bitmap
    lateinit var qrEncoder: QRGEncoder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loyaltypoints)

        qrIV = findViewById(R.id.qrcode)
        msgEdt = findViewById(R.id.textField)
        generateQRBtn = findViewById(R.id.generatebtn)

        generateQRBtn.setOnClickListener{
            if (TextUtils.isEmpty(msgEdt.text.toString())) {
                Toast.makeText(
                    applicationContext,
                    "Enter value to generate QR code e.g. $10,000", Toast.LENGTH_SHORT).show()
            } else {
                val windowManager: WindowManager = getSystemService(WINDOW_SERVICE) as WindowManager
                val display: Display = windowManager.defaultDisplay
                val point: Point = Point()
                display.getSize(point)
                val width = point.x
                val height = point.y
                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4
                qrEncoder = QRGEncoder(msgEdt.text.toString(), null, QRGContents.Type.TEXT, dimen)

                try {
                    bitmap = qrEncoder.encodeAsBitmap()
                    qrIV.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}

