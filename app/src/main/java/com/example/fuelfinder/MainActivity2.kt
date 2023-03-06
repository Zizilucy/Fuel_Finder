package com.example.fuelfinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.viewpager.widget.ViewPager

class MainActivity2 : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var sliderAdapter: SliderAdapter
    lateinit var sliderList: ArrayList<sliderdata>
    lateinit var skipBtn: Button
    lateinit var indicatorSlideOneTV: TextView
    lateinit var indicatorSlideTwoTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewPager = findViewById(R.id.idViewPager)
        skipBtn = findViewById(R.id.idBtnSkip)
        indicatorSlideOneTV = findViewById(R.id.idSlideOne)
        indicatorSlideTwoTV = findViewById(R.id.idSlideTwo)

        skipBtn.setOnClickListener {
            val i = Intent (this@MainActivity2, MainActivity2::class.java)
            startActivity(i)
        }
        sliderList = ArrayList()
        sliderList.add(
            sliderdata(
                "Spend and Gain",
                "Accumulate spendable coins every time you purchase from station found on Fuel Finder",
                R.drawable.coinstack
            )
        )
        sliderList.add(
            sliderdata(
                "Find stations with ease",
                "Never get stranded again with Fuel Finder, find fuel stations close to you anywhere, anytime.",
                R.drawable.fuelstation
            )
        )
        sliderAdapter = SliderAdapter(this, sliderList)
        viewPager.adapter = sliderAdapter
        viewPager.addOnPageChangeListener(viewListener)
    }
    var viewListener: ViewPager.OnPageChangeListener = object: ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            TODO("Not yet implemented")
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }
}